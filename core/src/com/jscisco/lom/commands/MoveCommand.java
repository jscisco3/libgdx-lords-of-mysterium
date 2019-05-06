package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Position3D;

public class MoveCommand extends Command {
    Position3D direction;

    public MoveCommand(Dungeon dungeon, Actor actor, int x, int y, int z) {
        super(dungeon, actor);
        this.direction = new Position3D(x, y, z);
    }

    public MoveCommand(Dungeon dungeon, Actor actor, Position3D direction) {
        super(dungeon, actor);
        this.direction = direction;
    }

    @Override
    public void invoke() {
        Position3D oldPosition = receiver.getPosition();
        Position3D newPosition = oldPosition.add(direction);
        if (dungeon.terrainIsWalkableAtPosition(newPosition)) {
            receiver.setPosition(newPosition);
        }
    }
}
