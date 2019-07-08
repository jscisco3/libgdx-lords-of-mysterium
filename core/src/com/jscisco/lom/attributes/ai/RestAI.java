package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.commands.RestCommand;
import com.jscisco.lom.dungeon.Dungeon;

public class RestAI implements AI {

    @Override
    public Command nextAction(Dungeon dungeon, Actor actor) {
        return new RestCommand(dungeon, actor);
    }
}
