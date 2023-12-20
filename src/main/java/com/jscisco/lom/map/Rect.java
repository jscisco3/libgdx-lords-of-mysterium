package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Rect {
    private final Position bottomLeft;
    private final Position topRight;

    public final int width;

    public final int height;

    public Rect(Position bottomLeft, Position topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.width = Math.abs(this.bottomLeft.getX() - this.topRight.getX());
        this.height = Math.abs(this.bottomLeft.getY() - this.topRight.getY());
    }

    public Rect(Position bottomLeft, int width, int height) {
        this.bottomLeft = bottomLeft;
        this.topRight = Position.of(bottomLeft.getX() + width, bottomLeft.getY() + height);
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rect other) {
        return this.bottomLeft.getX() <= other.topRight.getX() && this.topRight.getX() >= other.bottomLeft.getX()
                && this.bottomLeft.getY() <= other.topRight.getY() && this.topRight.getY() >= other.bottomLeft.getY();
    }

    public Position center() {
        return Position.of((this.bottomLeft.getX() + this.topRight.getX()) / 2,
                (this.bottomLeft.getY() + this.topRight.getY()) / 2);
    }

    public List<Position> points() {
        List<Position> points = new ArrayList<>();
        IntStream.range(bottomLeft.getX(), topRight.getX()).forEach(x -> {
            IntStream.range(bottomLeft.getY(), topRight.getY()).forEach(y -> {
                points.add(Position.of(x, y));
            });
        });
        return points;
    }

    public Position getBottomLeft() {
        return bottomLeft;
    }

    public Position getTopRight() {
        return topRight;
    }
}
