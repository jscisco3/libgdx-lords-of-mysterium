package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Position3D;

public class MoveAction extends Action {
    Position3D direction;

    public MoveAction(Dungeon dungeon, Entity entity, int x, int y, int z) {
        super(dungeon, entity);
        this.direction = new Position3D(x, y, z);
    }

    public MoveAction(Dungeon dungeon, Entity entity, Position3D direction) {
        super(dungeon, entity);
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
