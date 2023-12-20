package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Wall;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class BSPBuilder implements InitialMapBuilder {

    private final List<Rect> rects = new ArrayList<>();

    public BSPBuilder() {
    }

    @Override
    public void buildMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
        this.connectRooms(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        List<Rect> rooms = new ArrayList<>();
        this.rects.add(new Rect(Position.of(2, 2),
                Position.of(buildData.getLevel().width - 3, buildData.getLevel().height - 3)));
        Rect first = this.rects.getFirst();
        this.addSubRects(first);
        int numberOfRooms = 0;
        while (numberOfRooms < 240) {
            Rect room = this.getRandomRect(rng);
            Rect candidate = this.getRandomSubRect(room, rng);
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

    private void addSubRects(Rect rect) {
    }

    private boolean isPossible(Rect room, Level level) {
        Position expandedBottomLeft = Position.of(room.getBottomLeft().getX() - 2, room.getBottomLeft().getY() - 2);
        Position expandedTopRight = Position.of(room.getTopRight().getY() + 2, room.getTopRight().getY() + 2);
        Rect expanded = new Rect(expandedBottomLeft, expandedTopRight);
        // Check if we are in the bounds of the map.
        for (Position p : expanded.points()) {
            if (p.getX() > level.width - 2) {
                return false;
            }
            if (p.getY() > level.height - 2) {
                return false;
            }
            if (p.getX() < 1) {
                return false;
            }
            if (p.getY() < 1) {
                return false;
            }
            if (!(level.getTile(p).getFeature() instanceof Wall)) {
                return false;
            }
        }
        return true;
    }

    private Rect getRandomRect(RNG rng) {
        if (this.rects.size() == 1) {
            return this.rects.getFirst();
        }
        int roll = rng.between(0, this.rects.size());
        return this.rects.get(roll);
    }

    private Rect getRandomSubRect(Rect room, RNG rng) {
        int width = Math.max(3, rng.between(1, Math.min(room.width, 10)) - 1) + 1;
        int height = Math.max(3, rng.between(1, Math.min(room.height, 10)) - 1) + 1;
        Position bottomLeft = Position.of(rng.between(0, 5), rng.between(0, 5));
        Position topRight = Position.of(bottomLeft.getX() + width, bottomLeft.getY() + height);
        return new Rect(bottomLeft, topRight);
    }

    private void digCorridor(Level level, Position start, Position end) {
    }

}
