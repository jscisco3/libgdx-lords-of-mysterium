package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.commands.Action;
import com.jscisco.lom.dungeon.Dungeon;

public interface AI {

    public Action nextAction(Dungeon dungeon, Entity entity);

}
