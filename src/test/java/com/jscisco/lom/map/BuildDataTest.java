package com.jscisco.lom.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.zone.Feature;
import com.jscisco.lom.domain.zone.Floor;
import com.jscisco.lom.domain.zone.Wall;
import com.jscisco.lom.raws.RawMaster;
import org.junit.jupiter.api.Test;
import squidpony.squidmath.RNG;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BuildDataTest {

    @Test
    public void generatingADebugDungeonResultsInACorrectlySizedDungeon() {
        RNG rng = new RNG(0xDEADBEEFL);
        RawMaster raws = new RawMaster(new ObjectMapper());
        BuilderChain chain = new BuilderChain(1, 20, 20);
        chain.startWith(new DebugStarterBuilder()).build(rng, raws);

        Level level = chain.getBuildData().getLevel();
        assertThat(level.width).isEqualTo(20);
        assertThat(level.height).isEqualTo(20);

        // Count all walls and floors
        int wallCount = 0;
        int floorCount = 0;
        for (int i = 0; i < level.width; i++) {
            for (int j = 0; j < level.height; j++) {
                Feature f = level.tiles[i][j].getFeature();
                if (f instanceof Floor) {
                    floorCount += 1;
                }
                if (f instanceof Wall) {
                    wallCount += 1;
                }
            }
        }
        // 19 * 19
        int expectedFloors = 361;
        // 39 (400 - 361)
        int expectedWalls = 39;
        assertThat(floorCount).isEqualTo(expectedFloors);
        assertThat(wallCount).isEqualTo(expectedWalls);
    }
}
