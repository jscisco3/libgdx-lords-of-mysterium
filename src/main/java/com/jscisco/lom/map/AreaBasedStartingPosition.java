package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.math.DistanceAlgorithm;
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
    public void mutateMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        int seedX = 1;
        int seedY = 1;
        switch (this.x) {
        case LEFT -> seedX = 1;
        case CENTER -> seedX = buildData.getLevel().width / 2;
        case RIGHT -> seedX = buildData.getLevel().width - 2;
        }
        switch (this.y) {
        // TODO: Test this
        case TOP -> seedY = buildData.getLevel().height - 2;
        case CENTER -> seedY = buildData.getLevel().height / 2;
        case BOTTOM -> seedY = 1;
        }
        Position seed = Position.of(seedX, seedY);
        // Gather all available floors
        List<Position> floorIndices = buildData.getLevel().floorIndices();
        // Sort based on distance to point (SeedX, SeedY)
        floorIndices.sort((o1, o2) -> Float.compare(DistanceAlgorithm.euclideanSquared(seed, o1),
                DistanceAlgorithm.euclideanSquared(seed, o2)));

        // Use the first one.
        buildData.setStartingPosition(floorIndices.getFirst());
    }
}
