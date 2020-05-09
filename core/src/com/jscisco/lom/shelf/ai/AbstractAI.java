package com.jscisco.lom.shelf.ai;

import com.jscisco.lom.shelf.LOMGame_Deprecated;
import com.jscisco.lom.shelf.action.Action;
import com.jscisco.lom.shelf.entity.Entity;
import squidpony.squidmath.RNG;

public abstract class AbstractAI implements AI {

    protected RNG rng = LOMGame_Deprecated.rng;
    protected Entity entity;

    public AbstractAI(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Action nextAction() {
        return null;
    }

    public Entity getEntity() {
        return entity;
    }
}
