package com.jscisco.lom.attic.goap.actions;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AcquireRandomTargetAction extends GOAPAction {

    private static final Logger logger = LoggerFactory.getLogger(AcquireRandomTargetAction.class);

    public AcquireRandomTargetAction() {
        // We need a target
        addPrecondition(GOAPGoal.NEEDS_TARGET, true);
        // We now have a target
        addEffect(GOAPGoal.HAS_TARGET, true);
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
        // The entity now has a TARGET
        entity.updateWorldState(GOAPGoal.TARGET,
//                new Position(7, 7)
                entity.getStage().findEmptyPosition()
        );
        entity.updateWorldState(GOAPGoal.HAS_TARGET, true);
        entity.updateWorldState(GOAPGoal.NEEDS_TARGET, false);
//                entity.getStage().findEmptyPosition());
        // The action is complete
        this.finish();
        return true;
    }
}
