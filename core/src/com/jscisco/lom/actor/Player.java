package com.jscisco.lom.actor;

import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public class Player extends Actor {

    public Player(Position3D position, double radius) {
        this.position = position;
        this.fieldOfView = new FieldOfView(radius);
        this.health = new Health(100);
    }

    @Override
    public Command takeTurn() {
        return null;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }
}
