package com.jscisco.lom.domain;

import java.util.Objects;

public class Position {
    int x;
    int y;

    private Position() {
    }

    public static Position of(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("X and Y must not be negative");
        }
        Position p = new Position();
        p.x = x;
        p.y = y;
        return p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
