package com.jscisco.lom.domain;

import java.text.MessageFormat;

public enum Direction {
    N(Position.of(0, 1)), S(Position.of(0, -1)), W(Position.of(-1, 0)), E(Position.of(1, 0)), NE(Position.of(1, 1)),
    NW(Position.of(-1, 1)), SW(Position.of(-1, -1)), SE(Position.of(1, -1));

    public final Position relativePosition;

    private Direction(Position position) {
        this.relativePosition = position;
    }

    public static Direction byValue(Position p) {
        for (Direction d : values()) {
            if (d.relativePosition.equals(p)) {
                return d;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("Position {0} is not a valid direction", p.toString()));
    }

}
