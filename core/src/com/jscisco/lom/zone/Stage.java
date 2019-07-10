package com.jscisco.lom.zone;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.ActionResult;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.FOVCalculator;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.strategies.EmptyDungeonGenerationStrategy;
import com.jscisco.lom.zone.strategies.GenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a stage of the zone. Zones can 1 - n Stages.
 */
public class Stage {

    private static final Logger logger = LoggerFactory.getLogger(Stage.class);

    private Tile[][] tiles;
    private int width;
    private int height;
    private Player player;

    private List<Entity> entities;
    private int currentEntityIndex;

    private GenerationStrategy strategy;

    public Stage(int width, int height) {
        this(width, height, new EmptyDungeonGenerationStrategy(width, height));
    }

    public Stage(int width, int height, GenerationStrategy strategy) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.strategy = strategy;
        this.tiles = strategy.generate();

        this.currentEntityIndex = 0;
    }

    public void addEntity(Entity e) {
        this.entities.add(e);
    }

    public Position findEmptyPosition() {
        while (true) {
            int x = LOMGame.rng.between(0, width);
            int y = LOMGame.rng.between(0, height);
            if (this.tiles[x][y].getTerrain().isWalkable()) {
                return new Position(x, y);
            }
        }
    }

    public boolean terrainIsWalkableAtPosition(Position position) {
        return tiles[position.getX()][position.getY()].getTerrain().isWalkable();
    }

    public Terrain getTerrainAtPosition(Position position) {
        return tiles[position.getX()][position.getY()].getTerrain();
    }

    public Terrain getTerrainAtPosition(int x, int y) {
        return tiles[x][y].getTerrain();
    }

    public void updateBlocksBasedOnFOV() {
        FOVCalculator.calculateFOV(player, this);
        double[][] playerFov = player.getFieldOfView().getFov();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (playerFov[x][y] > 0.0) {
                    tiles[x][y].setSeen(true);
                    tiles[x][y].setInFov(true);
                } else {
                    tiles[x][y].setInFov(false);
                }
            }
        }
    }

    public Tile getTileAt(Position position) {
        return this.tiles[position.getX()][position.getY()];
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getEntityAtPosition(Position position) {
        for (Entity e : this.entities) {
            if (e.getPosition().equals(position)) {
                return e;
            }
        }
        return null;
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * This method processes the turns for all actors in the zone level
     */
    public void process() {
        // Continue processing entities until we get to a null action (e.g. waiting for player input
        while (true) {
            Entity entity = getCurrentEntity();
            logger.debug("The current entity is: {}", entity);
            Action action = entity.getNextAction();
            if (action == null) {
                return;
            }
            // Handle alternatives in a loop since an alternative could have an alternative
            while (true) {
                ActionResult result = action.invoke();
                if (!result.succeeded()) {
                    return;
                }
                if (result.getAlternative() == null) {
                    break;
                }
                action = result.getAlternative();
            }
            // Do not progress past this entity if their action failed.
            // Advance the current entity
            advanceEntity();
        }
    }

    private Entity getCurrentEntity() {
        return this.entities.get(this.currentEntityIndex);
    }

    private void advanceEntity() {
        this.currentEntityIndex = (this.currentEntityIndex + 1) % this.entities.size();
    }

}
