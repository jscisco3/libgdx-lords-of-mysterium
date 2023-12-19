package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;

public class Room {
    private final Position bottomLeft;
    private final Position topRight;

    public final int width;

    public final int height;

    public Room(Position bottomLeft, Position topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
        this.width = Math.abs(this.bottomLeft.getX() - this.topRight.getX());
        this.height = Math.abs(this.bottomLeft.getY() - this.topRight.getY());
    }

    public boolean intersects(Room other) {
        return this.bottomLeft.getX() <= other.topRight.getX() && this.topRight.getX() >= other.bottomLeft.getX()
                && this.bottomLeft.getY() <= other.topRight.getY() && this.topRight.getY() >= other.bottomLeft.getY();
    }

    public Position center() {
        return Position.of((this.bottomLeft.getX() + this.topRight.getX()) / 2, (this.bottomLeft.getY() + this.topRight.getY()) / 2);
    }
}
