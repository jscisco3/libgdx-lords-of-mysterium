package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.dungeon.Dungeon;

public class RestCommand extends Command {

    public RestCommand(Dungeon dungeon, Actor receiver) {
        super(dungeon, receiver);
    }

    @Override
    public void invoke() {
        super.invoke();
    }
}
