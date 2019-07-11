package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcquireTargetAction extends GOAPAction {

    private final static Logger logger = LoggerFactory.getLogger(AcquireTargetAction.class);

    public AcquireTargetAction() {
        addPrecondition(GOAPGoal.NEEDS_TARGET, true);
        addEffect(GOAPGoal.HAS_TARGET, true);
    }

    @Override
    public boolean requiresInRange() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        return true;
    }

    @Override
    public boolean perform(Entity entity) {
        this.setTarget(entity.getStage().getPlayer().getPosition());
        entity.updateWorldState(GOAPGoal.TARGET, this.target);
        this.finish();
        logger.info("Targeted: {}", this.target);
        return this.target != null;
    }
}
