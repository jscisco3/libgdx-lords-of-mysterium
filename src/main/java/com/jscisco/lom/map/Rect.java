package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;

import java.util.ArrayList;
import java.util.LinkedList;
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
        this.topRight = Position.of(bottomLeft.getX() + width - 1, bottomLeft.getY() + height - 1);
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

    /**
     * Divide the given rectangle vertically with a given width of the left subdivision
     *
     * @param width
     *            The width of the left subdivision
     *
     * @return The subdivided rectangles
     */
    public List<Rect> divideVertically(int width) {
        List<Rect> rects = new LinkedList<>();
        Rect left = new Rect(this.getBottomLeft(), width, height);
        Rect right = new Rect(Position.of(this.getBottomLeft().getX() + width, this.getBottomLeft().getY()),
                this.width - width, this.height);
        rects.add(left);
        rects.add(right);
        return rects;
    }

    /**
     * Divide the given rectangle horizontally with a particular height of the bottom rectangle
     *
     * @param height
     *            The height of the bottom subdivision
     *
     * @return The subdivided rectangles
     */
    public List<Rect> divideHorizontally(int height) {
        List<Rect> rects = new LinkedList<>();

        Rect bottom = new Rect(this.getBottomLeft(), this.width, height);
        Rect top = new Rect(Position.of(this.getBottomLeft().getX(), bottom.getBottomLeft().getY() + height),
                this.width, this.height - height);
        rects.add(top);
        rects.add(bottom);
        return rects;
    }

    @Override
    public String toString() {
        return "Rect{" + "bottomLeft=" + bottomLeft + ", topRight=" + topRight + ", width=" + width + ", height="
                + height + '}';
    }
}
