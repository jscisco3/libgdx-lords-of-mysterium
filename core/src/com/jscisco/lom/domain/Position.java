package com.jscisco.lom.domain;

import squidpony.squidmath.Coord;

import java.util.Objects;

public final class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(Position other) {
        return new Position(
                this.x + other.x(),
                this.y + other.y()
        );
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Coord asCoord() {
        return Coord.get(x, y);
    }


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Position get(int x, int y) {
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }

        Position other = (Position) obj;
        return this.x == other.x() && this.y == other.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
