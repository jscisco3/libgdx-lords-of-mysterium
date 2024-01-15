package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.raws.RawMaster;
import squidpony.squidmath.RNG;

public class RoomBasedStartingPosition implements MetaMapBuilder {
    @Override
    public void mutateMap(RNG rng, BuildData buildData, RawMaster raws) {
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
