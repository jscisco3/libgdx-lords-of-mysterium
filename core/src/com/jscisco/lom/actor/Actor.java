package com.jscisco.lom.actor;

import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public abstract class Actor {

    protected FieldOfView fieldOfView;
    protected Position3D position;
    protected Health health;

    public Position3D getPosition() {
        return position;
    }

    public void setPosition(Position3D position) {
        this.position = position;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getZ() {
        return position.getZ();
    }

    public Command takeTurn() {
        return null;
    }
}
