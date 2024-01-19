package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Level implements Cloneable {
    final Logger logger = LoggerFactory.getLogger(Level.class);
    // Representation of the level
    final int width;
    final int height;
    final int depth;

    // Contents of the level

    protected Tile[][] tiles;

    private final List<Item> items = new ArrayList<>();
    private final List<Entity> entities = new ArrayList<>();

    public Level(int depth, int width, int height) {
        this.depth = depth;
        this.width = width;
        this.height = height;
        this.tiles = new Tile[this.width][this.height];
        this.initializeTiles();
    }

    private void initializeTiles() {
        IntStream.range(0, this.width).forEach(x -> {
            IntStream.range(0, this.height).forEach(y -> {
                this.tiles[x][y] = TileFactory.wallTile();
            });
        });
    }

    public void setTile(int x, int y, Tile tile) {
        this.tiles[x][y] = tile;
    }

    public List<Position> floorIndices() {
        final List<Position> floorIndices = new ArrayList<>();
        IntStream.range(0, this.width).forEach(x -> {
            IntStream.range(0, this.height).forEach(y -> {
                if (this.tiles[x][y].getFeature() == Feature.FLOOR) {
                    floorIndices.add(Position.of(x, y));
                }
            });
        });
        return floorIndices;
    }

    public Tile getTile(Position p) {
        return this.tiles[p.getX()][p.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public void removeEntity(Entity e) {
        this.entities.remove(e);
    }

    public void addEntity(Entity e, Position position) {
        e.setPosition(position);
        this.entities.add(e);
    }

    public Entity getEntity(int index) {
        return this.entities.get(index);
    }

    public int getNumberOfEntities() {
        return this.entities.size();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public Level clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            // Maybe: Tiles, Items, Entities, more?
            return (Level) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
