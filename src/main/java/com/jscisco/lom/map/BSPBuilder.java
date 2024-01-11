package com.jscisco.lom.map;

import com.jscisco.lom.collections.Node;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.TileFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.util.List;

public class BSPBuilder implements InitialMapBuilder {

    private final Logger logger = LoggerFactory.getLogger(BSPBuilder.class);

    private Node<Rect> rects;
    // TODO: Move to a configuration file
    int minimumWidth = 10;
    int minimumHeight = 10;

    public BSPBuilder() {
    }

    @Override
    public void initializeMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
        this.connectRooms(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        // Start like this to ensure that we have walls surrounding us at the end.
        // TODO: Fix issue with subdividing rooms correctly.
        Rect root = new Rect(Position.of(1, 1), buildData.getLevel().width - 1, buildData.getLevel().height - 1);
        this.rects = new Node<>(root);
        int iterations = 5;
        for (int i = 0; i < iterations; i++) {
            // At each iteration, divide the leaf nodes in half. Add these children to the tree.
            // TODO: Future enhancements: Not dividing a room, where we divide, etc.
            List<Node<Rect>> leaves = this.rects.getLeaves();
            for (Node<Rect> leaf : leaves) {
                // Divide the leaf and add children. Divide them vertically to start
                // TODO: Randomize between horizontal and vertical slice
                // TODO: Homogeneity vs Heterogenity
                Rect r = leaf.getValue();
                logger.debug("Adding children");
                boolean horizontal = rng.nextBoolean();
                double multiplier = rng.between(0.25, 0.75);
                logger.info("Multiplier: " + multiplier);
                if (horizontal) {
                    int height = (int) (r.height * multiplier);
                    if (height > minimumHeight) {
                        for (Rect child : r.divideHorizontally(height)) {
                            leaf.addChild(new Node<>(child));
                            logger.debug("---> Child: " + child);
                        }
                    }
                } else {
                    int width = (int) (r.width * multiplier);
                    for (Rect child : r.divideVertically(width)) {
                        leaf.addChild(new Node<>(child));
                        logger.debug("---> Child: " + child);
                    }
                }
            }
        }
        // The dungeon has been divided. Now, let's dig the rooms
        for (Node<Rect> leaf : this.rects.getLeaves()) {
            // Create the room
            Rect r = leaf.getValue();
            boolean fullRooms = false;
            Rect room;
            if (fullRooms) {
                room = new Rect(r.getBottomLeft(), r.width, r.height);
            } else {
                int bottomLeftX = rng.between(r.getBottomLeft().getX(),
                        r.getBottomLeft().getX() + r.width - minimumWidth);
                int bottomLeftY = rng.between(r.getBottomLeft().getY(),
                        r.getBottomLeft().getY() + r.height - minimumHeight);
                Position bottomLeft = Position.of(bottomLeftX, bottomLeftY);
                room = new Rect(bottomLeft, rng.between(minimumWidth, r.width - bottomLeftX),
                        rng.between(minimumHeight, r.height - bottomLeftY));
            }
            Utils.applyRoomToLevel(buildData.getLevel(), room);
            buildData.takeSnapshot();
            leaf.addChild(new Node<>(room));
        }
        logger.info("Number of rooms generated: " + this.rects.getLeaves().size());
    }

    private void connectRooms(RNG rng, BuildData buildData) {
        List<Node<Rect>> rooms = this.rects.getLeaves();
        rooms.sort(
                (o1, o2) -> Float.compare(o1.getValue().getBottomLeft().getX(), o2.getValue().getBottomLeft().getX()));
        for (int i = 0; i < rooms.size() - 1; i++) {
            Rect room = rooms.get(i).getValue();
            Rect next = rooms.get(i + 1).getValue();
            int startX = room.getBottomLeft().getX() + rng.between(1, room.width) - 1;
            int startY = room.getBottomLeft().getY() + rng.between(1, room.height) - 1;
            int endX = next.getBottomLeft().getX() + rng.between(1, next.width - 1);
            int endY = next.getBottomLeft().getY() + rng.between(1, next.height) - 1;
            this.digCorridor(buildData.getLevel(), Position.of(startX, startY), Position.of(endX, endY));
            buildData.takeSnapshot();
        }
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
