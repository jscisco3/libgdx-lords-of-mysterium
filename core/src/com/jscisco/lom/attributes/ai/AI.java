package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.actor.Entity;

public interface AI {

    public Action nextAction(Entity entity);

}
