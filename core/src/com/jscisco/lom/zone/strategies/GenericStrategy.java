package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.repositories.TerrainRepository;
import com.jscisco.lom.zone.Tile;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;

public class GenericStrategy implements GenerationStrategy {

    @Override
    public Tile[][] generate(int width, int height) {
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
                if (map[x][y] == '#') {
                    tiles[x][y] = new Tile(TerrainRepository.WALL);
                } else {
                    tiles[x][y] = new Tile(TerrainRepository.FLOOR);
                }
            }
        }
        return tiles;
    }
}
