package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;

// TODO: Needed?
public class Connection {
    private Position startPosition;
    private Position endPosition;

    public Connection(Position startPosition, Position endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
