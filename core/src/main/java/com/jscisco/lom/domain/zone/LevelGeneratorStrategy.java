package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class LevelGeneratorStrategy {
    protected abstract List<List<Tile>> generate(int width, int height);

    public static class EmptyLevelStrategy extends LevelGeneratorStrategy {
        @Override
        protected List<List<Tile>> generate(int width, int height) {
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

    public static class GenericStrategy extends LevelGeneratorStrategy {
        @Override
        protected List<List<Tile>> generate(int width, int height) {
            Random random = new Random();
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
            // Now let us place some random walls
            for (int i = 0; i < 250; i++) {
                int x = random.nextInt(width - 6) + 5;
                int y = random.nextInt(height - 6) + 5;;
                tiles.get(x).set(y, TileFactory.wallTile());
            }

            // stairs down
            tiles.get(7).set(7, TileFactory.stairsDown());
            // stairs up
            tiles.get(10).set(7, TileFactory.stairsUp());
            return tiles;
        }
    }

}
