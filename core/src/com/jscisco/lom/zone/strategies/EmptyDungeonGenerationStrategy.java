package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.repositories.TerrainRepository;
import com.jscisco.lom.zone.Tile;

public class EmptyDungeonGenerationStrategy extends GenerationStrategy {

    public EmptyDungeonGenerationStrategy(int width, int height) {
        super(width, height);
    }

    @Override
    public Tile[][] generate() {
        Tile[][] tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(TerrainRepository.FLOOR);
            }
        }

        return tiles;
    }
}
