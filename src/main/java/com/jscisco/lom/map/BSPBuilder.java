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
        int numberOfRooms = 0;
        while (numberOfRooms < 240) {
            Room room = this.getRandomRoom(rng);
            Room candidate = this.getRandomSubRect(room, rng);
            if (this.isPossible(candidate, buildData.getLevel())) {
                Utils.applyRoomToLevel(buildData.getLevel(), candidate);
                rooms.add(candidate);
                this.rects.add(room);
            }
            numberOfRooms += 1;

        }
    }

    private void connectRooms(RNG rng, BuildData buildData) {
    }

    private void addSubRects(Room room) {
    }

    private boolean isPossible(Room room, Level level)  {
        return false;
    }

    private Room getRandomRoom(RNG rng) {
        if (this.rects.size() == 1) {
            return this.rects.getFirst();
        }
        int roll = rng.between(0, this.rects.size());
        return this.rects.get(roll);
    }

    private Room getRandomSubRect(Room room, RNG rng) {
        return null;
    }

    private void digCorridor(Level level, Position start, Position end) {}


}
