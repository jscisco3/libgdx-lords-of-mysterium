package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.math.DistanceAlgorithm;
import squidpony.squidmath.RNG;

import java.util.List;

public class RoomBasedStartingPosition implements MetaMapBuilder {
    @Override
    public void mutateMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        if (buildData.getRooms() != null && !buildData.getRooms().isEmpty()) {
            Position startingPosition = buildData.getRooms().getFirst().center();
            buildData.setStartingPosition(startingPosition);
        } else {
            throw new RuntimeException("Room based starting position only works after rooms have been created!");
        }
    }
}
