package com.jscisco.lom.zone;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.ActionResult;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.FOVCalculator;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.strategies.EmptyStageGenerationStrategy;
import com.jscisco.lom.zone.strategies.GenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a stage of the zone. Zones can 1 - n Stages.
 */
public class StageImpl implements Stage {

    private static final Logger logger = LoggerFactory.getLogger(Stage.class);

    private Tile[][] tiles;
    private int width;
    private int height;
    private Player player;

    private List<Entity> entities;
    private List<Item> items;
    private int currentEntityIndex;

    // Do I need to store this? Maybe, for saving and loading the stage.
    private GenerationStrategy strategy;

    public StageImpl(int width, int height) {
        this(width, height, new EmptyStageGenerationStrategy());
    }

    public StageImpl(int width, int height, GenerationStrategy strategy) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
        this.strategy = strategy;
        this.tiles = this.strategy.generate(this.width, this.height);

        this.currentEntityIndex = 0;
    }

    @Override
    public void addEntity(Entity e) {
        if (player == null && e instanceof Player) {
            setPlayer((Player) e);
        }
        this.entities.add(e);
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public List<Item> getItems() {
        return this.items;
    }

    @Override
    public void removeItem(Item item) {
        this.items.remove(item);
    }

    @Override
    public List<Item> getItemsAtPosition(Position position) {
        List<Item> foundItems = new ArrayList<>();
        for (Item item : this.items) {
            if (item.getPosition().equals(position)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    @Override
    public Position findEmptyPosition() {
        int attempt = 0;
        int maxAttempts = 100;
        while (attempt < maxAttempts) {
            int x = LOMGame.rng.between(0, width);
            int y = LOMGame.rng.between(0, height);
            if (this.tiles[x][y].getTerrain().isWalkable()) {
                return new Position(x, y);
            }
            attempt += 1;
        }
        return null;
    }

    @Override
    public boolean terrainIsWalkableAtPosition(Position position) {
        return tiles[position.getX()][position.getY()].getTerrain().isWalkable();
    }

    @Override
    public Terrain getTerrainAtPosition(Position position) {
        return getTerrainAtPosition(position.getX(), position.getY());
    }


    @Override
    public Terrain getTerrainAtPosition(int x, int y) {
        return tiles[x][y].getTerrain();
    }

    @Override
    public void updateTilesBasedOnFOV() {
        assert player != null;
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

    @Override
    public Tile getTileAt(Position position) {
        return this.tiles[position.getX()][position.getY()];
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public Entity getEntityAtPosition(Position position) {
        for (Entity e : this.entities) {
            if (e.getPosition().equals(position)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * This method processes the turns for all actors in the zone level
     */
    @Override
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
