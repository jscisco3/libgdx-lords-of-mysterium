package com.jscisco.lom.map;

import com.jscisco.lom.raws.RawMaster;
import squidpony.squidmath.RNG;

// TODO: Implement with spawn tables
public class RoomBasedSpawner implements MetaMapBuilder {

    @Override
    public void mutateMap(RNG rng, BuildData buildData, RawMaster raws) {
        if (buildData.getRooms() != null && !buildData.getRooms().isEmpty()) {
            for (Rect room : buildData.getRooms()) {

            }
        } else {
            throw new RuntimeException("Room based spawning only works after rooms have been created!");
        }
    }
}
