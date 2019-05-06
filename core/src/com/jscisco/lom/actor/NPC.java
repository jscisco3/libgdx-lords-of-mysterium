package com.jscisco.lom.actor;

import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public class NPC extends Actor {

    public NPC(Position3D position) {
        this.position = position;
    }

    @Override
    public Command takeTurn() {
        return super.takeTurn();
    }
}
