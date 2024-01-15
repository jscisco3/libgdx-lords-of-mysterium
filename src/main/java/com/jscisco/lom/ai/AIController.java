package com.jscisco.lom.ai;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.entity.Entity;

import java.util.UUID;

public abstract class AIController {

    private UUID id;

    protected Entity entity;

    public AIController() {
    }

    public AIController(Entity entity) {
        this.entity = entity;
    }

    public abstract Action getNextAction();

}
