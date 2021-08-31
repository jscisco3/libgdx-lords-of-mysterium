package com.jscisco.lom.domain;

import squidpony.squidmath.Coord;

import java.util.Objects;

public class Position {

    int x;
    int y;

    protected Position() {
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position add(Position position) {
        return of(this.x + position.x, this.y + position.y);
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public static Position UNKNOWN = Position.of(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Coord toCoord() {
        return Coord.get(x, y);
    }

    public static Position fromCoord(Coord coord) {
        return Position.of(coord.x, coord.y);
    }

    public Position subtract(Position p) {
        return Position.of(x - p.x, y - p.y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
