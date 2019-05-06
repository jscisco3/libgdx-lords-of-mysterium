package com.jscisco.lom.actor;

import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public class Player extends Actor {

    FieldOfView fieldOfView = new FieldOfView(10.0);

    public Player(Position3D position) {
        this.position = position;
    }

    @Override
    public Command takeTurn() {
        return null;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }
}
