package com.jscisco.lom.ai;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.commands.RestAction;
import com.jscisco.lom.dungeon.Dungeon;

public class Director {

    private Dungeon dungeon;

    public Director(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void setNextAction(Entity entity) {
        entity.setNextAction(new RestAction(this.dungeon, entity));
    }

}
