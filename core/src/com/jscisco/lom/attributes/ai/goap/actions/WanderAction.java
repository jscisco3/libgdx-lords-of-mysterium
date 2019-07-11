package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.entity.Entity;

public class WanderAction extends GOAPAction {
    public WanderAction() {
    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        return true;
    }

    @Override
    public boolean perform(Entity entity) {
        return super.perform(entity);
    }
}
