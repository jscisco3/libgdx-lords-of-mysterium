package com.jscisco.lom.zone;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.Position;

import java.util.List;

public interface Stage {

    void addEntity(Entity entity);

    Position findEmptyPosition();

    boolean terrainIsWalkableAtPosition(Position position);

    Terrain getTerrainAtPosition(Position position);

    Terrain getTerrainAtPosition(int x, int y);

    void updateBlocksBasedOnFOV();

    Tile getTileAt(Position position);

    Player getPlayer();

    void setPlayer(Player player);

    Tile[][] getTiles();

    List<Entity> getEntities();

    Entity getEntityAtPosition(Position position);

    void removeEntity(Entity entity);

    void process();

    int getHeight();

    int getWidth();

}
