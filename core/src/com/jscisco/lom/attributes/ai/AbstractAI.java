package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.LOMGame_Deprecated;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.entity.Entity;
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
