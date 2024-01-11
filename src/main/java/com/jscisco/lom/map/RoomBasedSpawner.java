package com.jscisco.lom.map;

import squidpony.squidmath.RNG;

import java.util.stream.Collectors;

// TODO: Implement with spawn tables
public class RoomBasedSpawner implements MetaMapBuilder {

    @Override
    public void mutateMap(RNG rng, BuildData buildData) {
        if (buildData.getRooms() != null && !buildData.getRooms().isEmpty()) {
            for (Rect room : buildData.getRooms()) {

            }
        } else {
            throw new RuntimeException("Room based spawning only works after rooms have been created!");
        }
    }
}
