package com.jscisco.lom.map;

import com.jscisco.lom.collections.Node;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.TileFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSPBuilder implements InitialMapBuilder {

    private final Logger logger = LoggerFactory.getLogger(BSPBuilder.class);

    private Node<Rect> rects;
    // TODO: Move to a configuration file
    int minimumWidth = 5;
    int minimumHeight = 5;

    public BSPBuilder() {
    }

    @Override
    public void buildMap(RNG rng, BuildData buildData) {
        this.build(rng, buildData);
        this.connectRooms(rng, buildData);
    }

    private void build(RNG rng, BuildData buildData) {
        // Start like this to ensure that we have walls surrounding us at the end.
        Rect root = new Rect(Position.of(1, 1), buildData.getLevel().width - 1, buildData.getLevel().height - 1);
        this.rects = new Node<>(root);
        int iterations = 4;
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
                if (horizontal) {
                    for (Rect child : r.divideHorizontally(leaf.getValue().height / 2)) {
                        leaf.addChild(new Node<>(child));
                        logger.debug("---> Child: " + child);
                    }
                } else {
                    for (Rect child : r.divideVertically(leaf.getValue().width / 2)) {
                        leaf.addChild(new Node<>(child));
                        logger.debug("---> Child: " + child);
                    }
                }
            }
        }
        // The dungeon has been divided. Now, let's dig the rooms
        // TODO: For each leaf, create a smaller room and add it as a child to support connecting the rooms at the end
        for (Node<Rect> leaf : this.rects.getLeaves()) {
            Position bottomLeft = leaf.getValue().getBottomLeft();
            Rect room = new Rect(Position.of(bottomLeft.getX(), bottomLeft.getY()), leaf.getValue().width,
                    leaf.getValue().height);
            Utils.applyRoomToLevel(buildData.getLevel(), room);
            buildData.takeSnapshot();
        }
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
