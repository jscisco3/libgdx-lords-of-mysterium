package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.terrain.Floor;
import com.jscisco.lom.zone.Tile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEmptyGenerationStrategy {

    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private GenerationStrategy strategy = new EmptyDungeonGenerationStrategy(WIDTH, HEIGHT);

    @Test
    public void emptyGenerationStrategyShouldReturnCorrectDimensions() {
        Tile[][] tiles = this.strategy.generate();
        Assertions.assertThat(tiles.length).isEqualTo(WIDTH);
        Assertions.assertThat(tiles[0].length).isEqualTo(HEIGHT);
    }

    @Test
    public void emptyGenerationStrategyShouldYieldOnlyFloor() {
        Tile[][] tiles = this.strategy.generate();
        int nonFloor = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (!(tiles[x][y].getTerrain() instanceof Floor)) {
                    nonFloor += 1;
                }
            }
        }
        Assertions.assertThat(nonFloor).isEqualTo(0);
    }

}
