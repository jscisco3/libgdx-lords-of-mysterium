package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.entity.Entity;

public class AcquireTargetAction extends GOAPAction {

    Entity target;

    public AcquireTargetAction() {
        addEffect(GOAPGoal.HAS_TARGET, true);
    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        return super.checkProceduralPreconditions(entity);
    }

    @Override
    public boolean perform(Entity entity) {
        this.target = entity.getStage().getPlayer();
        return this.target != null;
    }
}
