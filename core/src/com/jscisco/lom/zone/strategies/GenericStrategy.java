package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.terrain.TerrainRepository;
import com.jscisco.lom.zone.Tile;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;
import squidpony.squidmath.Coord;

public class GenericStrategy implements GenerationStrategy {

    @Override
    public Tile[][] generate(int width, int height, boolean stairsUp, boolean stairsDown) {
        Tile[][] tiles = new Tile[width][height];
        char[][] map;

        DungeonGenerator generator = new DungeonGenerator(width, height);
        generator.addDoors(25, false);
        SerpentMapGenerator serpent = new SerpentMapGenerator(width, height, LOMGame.rng, 0.2);
        serpent.putWalledBoxRoomCarvers(2);
        serpent.putWalledRoundRoomCarvers(2);
        serpent.putCaveCarvers(2);
        map = serpent.generate();
        DungeonUtility.closeDoors(generator.generate(map));

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                switch (map[x][y]) {
                    case '#':
                        tiles[x][y] = new Tile(TerrainRepository.WALL);
                        break;
                    case '+':
                    case '/':
                    default:
                        tiles[x][y] = new Tile(TerrainRepository.FLOOR);
                        break;
                }
            }
        }

        if (stairsDown) {
            Coord coordStairsDown = generator.stairsDown;
            tiles[coordStairsDown.x][coordStairsDown.y] = new Tile(TerrainRepository.STAIRS_DOWN);
        }
        if (stairsUp) {
            Coord coordStairsUp = generator.stairsUp;
            tiles[coordStairsUp.x][coordStairsUp.y] = new Tile(TerrainRepository.STAIRS_UP);
        }

        return tiles;
    }
}
