package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.dungeon.Dungeon;

public interface AI {

    public Command nextAction(Dungeon dungeon, Actor actor);

}
