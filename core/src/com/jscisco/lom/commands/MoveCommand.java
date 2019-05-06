package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.util.Position3D;

public class MoveCommand implements Command {

    Position3D direction;

    public MoveCommand(int x, int y, int z) {
        this.direction = new Position3D(x, y, z);
    }

    public MoveCommand(Position3D direction) {
        this.direction = direction;
    }

    @Override
    public void invoke(Actor actor) {
        Position3D oldPosition = actor.getPosition();
        Position3D newPosition = oldPosition.add(direction);
        actor.setPosition(newPosition);
    }
}
