package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.commands.Action;
import com.jscisco.lom.commands.RestAction;
import com.jscisco.lom.dungeon.Dungeon;

public class RestAI implements AI {

    @Override
    public Action nextAction(Dungeon dungeon, Entity entity) {
        return new RestAction(dungeon, entity);
    }
}
