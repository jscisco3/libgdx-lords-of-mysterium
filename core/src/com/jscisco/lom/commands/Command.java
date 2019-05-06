package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.dungeon.Dungeon;

public abstract class Command {

    protected Dungeon dungeon;
    protected Actor receiver;

    public Command(Dungeon dungeon, Actor receiver) {
        this.dungeon = dungeon;
        this.receiver = receiver;
    }

    public void invoke() {
    }
}
