package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public abstract class AIController {

    public abstract Action getNextAction(Entity entity);

}
