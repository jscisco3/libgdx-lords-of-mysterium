package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.dungeon.Dungeon;

public abstract class Action {

    protected Dungeon dungeon;
    protected Entity receiver;

    public Action(Dungeon dungeon, Entity receiver) {
        this.dungeon = dungeon;
        this.receiver = receiver;
    }

    public void invoke() {
    }
}
