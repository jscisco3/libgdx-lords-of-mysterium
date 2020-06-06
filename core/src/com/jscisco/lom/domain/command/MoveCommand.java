package com.jscisco.lom.domain.command;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.GameObject;

public class MoveCommand extends Command {

    private Position position;

    public MoveCommand(GameObject entity, Position position) {
        super(entity);
        this.position = position;
    }

    @Override
    public void execute() {
        this.entity.moveTo(position);
    }
}
