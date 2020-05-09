package com.jscisco.lom.shelf.zone.strategies;

import com.jscisco.lom.shelf.terrain.Floor;
import com.jscisco.lom.shelf.terrain.Wall;
import com.jscisco.lom.shelf.zone.Tile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEmptyStageGenerationStrategy {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private GenerationStrategy strategy = new EmptyStageGenerationStrategy();

    @Test
    public void emptyGenerationStrategyShouldReturnCorrectDimensions() {
        Tile[][] tiles = this.strategy.generate(WIDTH, HEIGHT, false, false);
        Assertions.assertThat(tiles.length).isEqualTo(WIDTH);
        Assertions.assertThat(tiles[0].length).isEqualTo(HEIGHT);
    }


    /**
     * Given an empty generation strategy
     * When we generate a stage without stairs up or down
     * Then we should only have walls and floors
     */
    @Test
    public void emptyGenerationStrategyShouldYieldOnlyFloorAndWalls() {
        Tile[][] tiles = this.strategy.generate(WIDTH, HEIGHT, false, false);
        int nonFloor = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (!(tiles[x][y].getTerrain() instanceof Floor) && !(tiles[x][y].getTerrain() instanceof Wall)) {
                    nonFloor += 1;
                }
            }
        }
        Assertions.assertThat(nonFloor).isEqualTo(0);
    }

}
