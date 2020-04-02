package com.jscisco.lom.zone;

import com.jscisco.lom.LOMGame_Deprecated;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.ActionResult;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.terrain.StairsDown;
import com.jscisco.lom.terrain.StairsUp;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.FOVCalculator;
import com.jscisco.lom.zone.strategies.EmptyStageGenerationStrategy;
import com.jscisco.lom.zone.strategies.GenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This represents a stage of the zone. Zones can 1 - n Stages.
 */
@Deprecated
public class Stage {

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

    private Optional<Position> stairsUpPosition;
    private Optional<Position> stairsDownPosition;

    public Stage() {
        this(20, 20);
    }

    public Stage(int width, int height) {
        this(width, height, false, false, new EmptyStageGenerationStrategy());
    }

    public Stage(int width, int height, boolean stairsUp, boolean stairsDown, GenerationStrategy strategy) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
        this.strategy = strategy;
        this.tiles = this.strategy.generate(this.width, this.height, stairsUp, stairsDown);

        this.stairsDownPosition = getPositionOfStairsDown();
        this.stairsUpPosition = getPositionOfStairsUp();

        this.currentEntityIndex = 0;
    }

    public void addEntity(Entity e) {
        if (player == null && e instanceof Player) {
            setPlayer((Player) e);
        }
        this.entities.add(e);
        // Make sure the entity has the right stage
        e.setStage(this);
        // And that their pathing map is updated with the appropriate stage
        e.updatePathingMap();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public List<Item> getItemsAtPosition(Position position) {
        List<Item> foundItems = new ArrayList<>();
        this.items.stream()
                .filter(item -> item.getPosition().isPresent() && item.getPosition().get().equals(position))
                .findFirst().ifPresent(foundItems::add);
        return foundItems;
    }

    public Optional<Position> findEmptyPosition() {
        int attempt = 0;
        int maxAttempts = 100;
        while (attempt < maxAttempts) {
            int x = LOMGame_Deprecated.rng.between(0, width);
            int y = LOMGame_Deprecated.rng.between(0, height);
            if (this.tiles[x][y].getTerrain().isWalkable()) {
                return Optional.of(new Position(x, y));
            }
            attempt += 1;
        }
        return Optional.empty();
    }

    public boolean terrainIsWalkableAtPosition(Position position) {
        return tiles[position.x()][position.y()].getTerrain().isWalkable();
    }

    public Terrain getTerrainAtPosition(Position position) {
        return getTerrainAtPosition(position.x(), position.y());
    }


    public Terrain getTerrainAtPosition(int x, int y) {
        return tiles[x][y].getTerrain();
    }

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

    public Tile getTileAt(Position position) {
        return this.tiles[position.x()][position.y()];
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
                // Do not progress past this entity if their action failed.
                if (!result.succeeded()) {
                    return;
                }
                // We do not have an alternative, so we're good!
                if (result.getAlternative() == null) {
                    break;
                }
                action = result.getAlternative();
            }
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

    public Optional<Position> getStairsUpPosition() {
        return stairsUpPosition;
    }

    public Optional<Position> getStairsDownPosition() {
        return stairsDownPosition;
    }

    private Optional<Position> getPositionOfStairsUp() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y].getTerrain() instanceof StairsUp) {
                    return Optional.of(new Position(x, y));
                }
            }
        }
        return Optional.empty();
    }

    private Optional<Position> getPositionOfStairsDown() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y].getTerrain() instanceof StairsDown) {
                    return Optional.of(new Position(x, y));
                }
            }
        }
        return Optional.empty();
    }

    public char[][] toSquidlibMap() {
        char[][] map = new char[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Terrain t = tiles[x][y].getTerrain();
                if (t.isWalkable()) {
                    map[x][y] = '.';
                } else {
                    map[x][y] = '#';
                }
            }
        }
        return map;
    }
}
