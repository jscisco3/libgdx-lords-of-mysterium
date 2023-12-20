package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.TileFactory;
import com.jscisco.lom.domain.zone.Wall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class BSPBuilder implements InitialMapBuilder {
    private final Logger logger = LoggerFactory.getLogger(BSPBuilder.class);

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
            logger.info(MessageFormat.format("numberOfRooms: {0}", numberOfRooms));
            Rect room = this.getRandomRect(rng);
            Rect candidate = this.getRandomSubRect(room, rng);
            if (this.isPossible(candidate, buildData.getLevel())) {
                logger.info("Applying candidate...");
                Utils.applyRoomToLevel(buildData.getLevel(), candidate);
                rooms.add(candidate);
                buildData.takeSnapshot();
                this.rects.add(room);
                this.addSubRects(room);
            }
            numberOfRooms += 1;
        }
        buildData.setRooms(rooms);
    }

    private void connectRooms(RNG rng, BuildData buildData) {
        List<Rect> rooms = buildData.getRooms();
        rooms.sort((o1, o2) -> Float.compare(o1.getBottomLeft().getX(), o2.getBottomLeft().getX()));
        for (int i = 0; i < rooms.size() - 1; i++) {
            Rect room = rooms.get(i);
            Rect next = rooms.get(i + 1);
            int startX = room.getBottomLeft().getX() + rng.between(1, room.width) - 1;
            int startY = room.getBottomLeft().getY() + rng.between(1, room.height) - 1;
            int endX = next.getBottomLeft().getX() + rng.between(1, next.width - 1);
            int endY = next.getBottomLeft().getY() + rng.between(1, next.height) - 1;
            this.digCorridor(buildData.getLevel(), Position.of(startX, startY), Position.of(endX, endY));
            buildData.takeSnapshot();
        }
    }

    private void addSubRects(Rect rect) {
        Position bottomLeft = rect.getBottomLeft();
        Position topRight = rect.getTopRight();
        int halfWidth = Math.max(rect.width / 2, 1);
        int halfHeight = Math.max(rect.height / 2, 1);
        this.rects.add(new Rect(bottomLeft, halfWidth, halfHeight));
        this.rects.add(new Rect(Position.of(bottomLeft.getX(), bottomLeft.getY() + halfHeight), halfWidth, halfHeight));
        this.rects.add(new Rect(Position.of(bottomLeft.getX() + halfWidth, bottomLeft.getY()), halfWidth, halfHeight));
        this.rects.add(new Rect(Position.of(bottomLeft.getX() + halfWidth, bottomLeft.getY() + halfHeight), halfWidth,
                halfHeight));
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
        logger.info("Getting subrect from ({}, {})", bottomLeft, topRight);
        return new Rect(bottomLeft, topRight);
    }

    private void digCorridor(Level level, Position start, Position end) {
        int x = start.getX();
        int y = start.getY();
        while (x != end.getX() || y != end.getY()) {
            if (x < end.getX()) {
                x += 1;
            } else if (x > end.getX()) {
                x -= 1;
            } else if (y < end.getY()) {
                y += 1;
            } else if (y > end.getY()) {
                y -= 1;
            }
            level.setTile(x, y, TileFactory.floorTile());
        }
    }

}
