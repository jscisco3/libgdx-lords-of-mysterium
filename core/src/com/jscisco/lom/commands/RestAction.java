package com.jscisco.lom.commands;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.dungeon.Dungeon;

public class RestAction extends Action {

    public RestAction(Dungeon dungeon, Entity receiver) {
        super(dungeon, receiver);
    }

    @Override
    public void invoke() {
        super.invoke();
    }
}
