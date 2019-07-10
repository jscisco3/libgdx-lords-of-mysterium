package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.repositories.TerrainRepository;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.zone.Tile;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;

public class GenericStrategy extends GenerationStrategy {

    public GenericStrategy(int width, int height) {
        super(width, height);
    }

    @Override
    public Tile[][] generate() {
        Tile[][] tiles = new Tile[width][height];
        char[][] map;
        Terrain[][] terrainMap = new Terrain[width][height];

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
