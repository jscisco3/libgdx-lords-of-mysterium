package com.jscisco.lom.map;

// TODO: Migrate to `map` package

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Floor;
import com.jscisco.lom.domain.zone.Tile;
import com.jscisco.lom.domain.zone.TileFactory;
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

    private List<Item> items;
    private List<Entity> entities = new ArrayList<>();

    // Whose turn it is.
    // TODO: This should probably not be here, instead elsewhere.
    private int currentActorIndex = 0;

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
                if (this.tiles[x][y].getFeature() instanceof Floor) {
                    floorIndices.add(Position.of(x, y));
                }
            });
        });
        return floorIndices;
    }

    public Tile getTile(Position p) {
        return this.tiles[p.getX()][p.getY()];
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
