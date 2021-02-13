package com.jscisco.lom.domain;

public enum Direction {
    N(Position.of(0, 1)),
    S(Position.of(0, -1)),
    W(Position.of(-1, 0)),
    E(Position.of(1, 0)),
    NE(Position.of(1, 1)),
    NW(Position.of(-1, 1)),
    SW(Position.of(-1, -1)),
    SE(Position.of(1, -1));

    public final Position relativePosition;

    private Direction(Position position) {
        this.relativePosition = position;
    }

}
