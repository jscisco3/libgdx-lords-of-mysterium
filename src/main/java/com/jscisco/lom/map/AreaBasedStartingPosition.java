package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import squidpony.squidmath.RNG;

import java.util.List;

public class AreaBasedStartingPosition implements MetaMapBuilder {
    final XStart x;
    final YStart y;

    public AreaBasedStartingPosition(XStart x, YStart y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void build_map(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        int seedX;
        int seedY;
        switch (this.x) {
            case LEFT -> seedX = 1;
            case CENTER -> seedX = buildData.getLevel().width / 2;
            case BOTTOM -> seedX = buildData.getLevel().width - 2;
        }
        switch (this.y) {
            // TODO: Test this
            case TOP -> seedY = 1;
            case CENTER -> seedY = buildData.getLevel().height / 2;
            case BOTTOM -> seedY = buildData.getLevel().height - 2;
        }
        // Gather all available floors
        List<Position> floorIndices = buildData.getLevel().floorIndices();
        // Sort based on distance to point (SeedX, SeedY)

        // Use the first one.

    }
}
