package com.jscisco.lom.domain.zone;

import java.util.ArrayList;
import java.util.List;

public abstract class LevelGeneratorStrategy {

    protected final int width;
    protected final int height;

    public LevelGeneratorStrategy(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected abstract List<List<Tile>> generate();

    public static class EmptyLevelStrategy extends LevelGeneratorStrategy {
        public EmptyLevelStrategy(int width, int height) {
            super(width, height);
        }

        @Override
        protected List<List<Tile>> generate() {
            List<List<Tile>> tiles = new ArrayList<>();
            // Let's first create the floors.
            for (int i = 0; i < width; i++) {
                List<Tile> column = new ArrayList<>();
                for (int j = 0; j < height; j++) {
                    column.add(TileFactory.floorTile());
                }
                tiles.add(column);
            }
            // Now let's do the top and bottom walls
            for (int i = 0; i < width; i++) {
                tiles.get(i).set(0, TileFactory.wallTile());
                tiles.get(i).set(height - 1, TileFactory.wallTile());
            }
            // Left and right walls
            for (int i = 0; i < height; i++) {
                tiles.get(0).set(i, TileFactory.wallTile());
                tiles.get(width - 1).set(i, TileFactory.wallTile());
            }

            // stairs down
            tiles.get(7).set(7, TileFactory.stairsDown());
            // stairs up
            tiles.get(10).set(7, TileFactory.stairsUp());
            return tiles;
        }
    }
}
