package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.MathUtils;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.cellular_automata.Cell;
import com.jscisco.lom.domain.cellular_automata.CellularAutomata;
import com.jscisco.lom.domain.cellular_automata.GameOfLifeRuleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidgrid.mapping.GrowingTreeMazeGenerator;
import squidpony.squidgrid.mapping.IDungeonGenerator;
import squidpony.squidgrid.mapping.SerpentMapGenerator;

import java.text.MessageFormat;

public abstract class LevelGeneratorStrategy {
    protected static final Logger logger = LoggerFactory.getLogger(LevelGeneratorStrategy.class);

    public abstract Tile[][] generate(int width, int height);

    public enum Strategy {
        EMPTY,
        GENERIC,
        RANDOM_ROOM,
        CELLULAR_AUTOMATA;
    }

    public static class EmptyLevelStrategy extends LevelGeneratorStrategy {
        @Override
        public Tile[][] generate(int width, int height) {
            Tile[][] tiles = LevelGeneratorStrategy.initialize(width, height);
            // TODO: Place stairs randomly
            return tiles;
        }
    }

    public static class GenericStrategy extends LevelGeneratorStrategy {
        @Override
        public Tile[][] generate(int width, int height) {
            IDungeonGenerator generator = new SerpentMapGenerator(width, height, GameConfiguration.rng);
            char[][] dungeon = generator.generate();
            Tile[][] tiles = new Tile[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (dungeon[x][y] == '#') {
                        tiles[x][y] = TileFactory.wallTile();
                    } else if (dungeon[x][y] == '.') {
                        tiles[x][y] = TileFactory.floorTile();
                    } else {
                        logger.warn(MessageFormat.format("At position ({0}, {1}), we have unknown char {2} -- replacing with floor", x, y, dungeon[x][y]));
                        tiles[x][y] = TileFactory.floorTile();
                    }
                }
            }

            // stairs down
//            tiles.get(7).set(7, TileFactory.stairsDown());
//            // stairs up
//            tiles.get(10).set(7, TileFactory.stairsUp());
            return tiles;
        }
    }

    public static class RandomRoomStrategy extends LevelGeneratorStrategy {
        @Override
        public Tile[][] generate(int width, int height) {
            Tile[][] tiles = allWalls(width, height);
            for (int i = 0; i < 10; i++) {
                int w = MathUtils.randomIntegerInRange(GameConfiguration.random, 4, 10);
                int h = MathUtils.randomIntegerInRange(GameConfiguration.random, 4, 10);
                int x = MathUtils.randomIntegerInRange(GameConfiguration.random, 1, width - 11);
                int y = MathUtils.randomIntegerInRange(GameConfiguration.random, 1, height - 11);
                Room room = new Room(w, h, Position.of(x, y));
                for (Position p : room.getPoints()) {
                    tiles[p.getX()][p.getY()].setFeature(FeatureFactory.FLOOR);
                }
            }
            return tiles;
        }
    }

    public static class CellularAutomataStrategy extends LevelGeneratorStrategy {

        @Override
        public Tile[][] generate(int width, int height) {
            logger.info("Generating...");
            Tile[][] tiles = allWalls(width, height);
            Cell[][] seed = new Cell[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    seed[i][j] = new Cell(Cell.State.ALIVE);
                }
            }
            // Set the outer cells to definitively dead
            for (int i = 0; i < width; i++) {
                seed[i][0] = new Cell(Cell.State.DEFINITIVELY_DEAD);
                seed[i][height - 1] = new Cell(Cell.State.DEFINITIVELY_DEAD);
            }
            for (int i = 0; i < height; i++) {
                seed[0][i] = new Cell(Cell.State.DEFINITIVELY_DEAD);
                seed[width - 1][i] = new Cell(Cell.State.DEFINITIVELY_DEAD);
            }
            CellularAutomata automata = new CellularAutomata(seed, new GameOfLifeRuleSet());

            for (int i = 0; i < 100; i++) {
                automata.tick();
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Cell c = automata.getCell(Position.of(i, j));
                    if (c.isAlive()) {
                        tiles[i][j].setFeature(FeatureFactory.FLOOR);
                    } else {
                        tiles[i][j].setFeature(FeatureFactory.WALL);
                    }
                }
            }
            return tiles;
        }
    }


    public static Tile[][] allWalls(int width, int height) {
        // Let's first create the floors.
        Tile[][] tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = TileFactory.wallTile();
            }
        }
        return tiles;
    }

    private static Tile[][] initialize(int width, int height) {
        // Let's first create the floors.
        Tile[][] tiles = new Tile[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = TileFactory.floorTile();
            }
        }
        // Now let's do the top and bottom walls
        for (int i = 0; i < width; i++) {
            tiles[i][0] = TileFactory.wallTile();
            tiles[i][height - 1] = TileFactory.wallTile();
        }
        // Left and right walls
        for (int i = 0; i < height; i++) {
            tiles[0][i] = TileFactory.wallTile();
            tiles[width - 1][i] = TileFactory.wallTile();
        }
        return tiles;
    }

}
