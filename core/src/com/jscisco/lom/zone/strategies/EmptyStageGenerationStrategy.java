package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.repositories.TerrainRepository;
import com.jscisco.lom.zone.Tile;

public class EmptyStageGenerationStrategy implements GenerationStrategy {

    @Override
    public Tile[][] generate(int width, int height) {
        Tile[][] tiles = new Tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(TerrainRepository.FLOOR);
            }
        }
        initializeOutsideWalls(tiles);
        return tiles;
    }

    private void initializeOutsideWalls(Tile[][] tiles) {
        int width = tiles.length;
        int height = tiles[0].length;
        for (int x = 0; x < width; x++) {
            tiles[x][0].setTerrain(TerrainRepository.WALL);
            tiles[x][height - 1].setTerrain(TerrainRepository.WALL);
        }
        for (int y = 0; y < height; y++) {
            tiles[0][y].setTerrain(TerrainRepository.WALL);
            tiles[width - 1][y].setTerrain(TerrainRepository.WALL);
        }
    }
}
