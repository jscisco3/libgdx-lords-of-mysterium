package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class BSPBuilder implements InitialMapBuilder {

    private final List<Room> rects = new ArrayList<>();

    public BSPBuilder() {
    }

    @Override
    public void buildMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
        this.connectRooms(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        List<Room> rooms = new ArrayList<>();
        this.rects.add(new Room(
                Position.of(2, 2),
                Position.of(buildData.getLevel().width - 3,
                        buildData.getLevel().height - 3)
        ));
        Room first = this.rects.getFirst();
        this.addSubRects(first);
    }

    private void connectRooms(RNG rng, BuildData buildData) {
    }

    private void addSubRects(Room room) {
    }
}
