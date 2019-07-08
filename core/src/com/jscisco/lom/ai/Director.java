package com.jscisco.lom.ai;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.RestCommand;
import com.jscisco.lom.dungeon.Dungeon;

public class Director {

    private Dungeon dungeon;

    public Director(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void setNextAction(Actor actor) {
        actor.setNextCommand(new RestCommand(this.dungeon, actor));
    }

}
