package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int width;
    private final int height;
    private final Position bottomLeft;
    private List<Position> points;

    public Room(int width, int height, Position bottomLeft) {
        this.width = width;
        this.height = height;
        this.bottomLeft = bottomLeft;
        initializePoints();
    }

    private void initializePoints() {
        this.points = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.points.add(bottomLeft.add(Position.of(i, j)));
            }
        }
    }

    public List<Position> getPoints() {
        return points;
    }
}
