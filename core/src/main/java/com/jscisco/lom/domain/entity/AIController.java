package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public abstract class AIController {

    protected final Entity entity;

    public AIController(Entity entity) {
        this.entity = entity;
    }

    public abstract Action getNextAction();

}
