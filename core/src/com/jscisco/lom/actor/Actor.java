package com.jscisco.lom.actor;

import com.jscisco.lom.util.Position3D;

public abstract class Actor {

    Position3D position;

    public Position3D getPosition() {
        return position;
    }

    public void setPosition(Position3D position) {
        this.position = position;
    }
}
