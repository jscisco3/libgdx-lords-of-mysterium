package com.jscisco.lom.zone;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.Position;

import java.util.List;

public interface Stage {

    Player getPlayer();

    void setPlayer(Player player);

    void addEntity(Entity entity);

    List<Entity> getEntities();

    Entity getEntityAtPosition(Position position);

    void removeEntity(Entity entity);

    void addItem(Item item);

    List<Item> getItems();

    void removeItem(Item item);

    List<Item> getItemsAtPosition(Position position);

    Position findEmptyPosition();

    boolean terrainIsWalkableAtPosition(Position position);

    Terrain getTerrainAtPosition(Position position);

    Terrain getTerrainAtPosition(int x, int y);

    void updateTilesBasedOnFOV();

    Tile getTileAt(Position position);

    Tile[][] getTiles();

    /**
     * Iterate over entities, get their actions and invoke them as appropriate
     */
    void process();

    /**
     * All Stage implementations should have a height
     * @return height of the stage
     */
    int getHeight();

    /**
     * All Stage implementations should have a width
     * @return width of the stage
     */
    int getWidth();

    Position getPositionOfStairsDown();

    Position getPositionOfStairsUp();

}
