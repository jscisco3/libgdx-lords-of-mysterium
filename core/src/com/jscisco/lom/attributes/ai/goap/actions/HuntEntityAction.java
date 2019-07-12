package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.action.AttackAction;
import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HuntEntityAction extends GOAPAction {

    private static final Logger logger = LoggerFactory.getLogger(HuntEntityAction.class);

    private Entity prey;

    public HuntEntityAction() {
        addPrecondition(GOAPGoal.TARGET_DESTROYED, false);
        addPrecondition(GOAPGoal.HAS_TARGET, true);
        addEffect(GOAPGoal.TARGET_DESTROYED, true);
    }

    @Override
    public void reset() {
        this.prey = null;
    }

    @Override
    public boolean checkProceduralPreconditions(Entity entity) {
        prey = entity.getStage().getEntityAtPosition(this.target);
        return prey != null;
    }

    @Override
    public boolean perform(Entity entity) {
        entity.setNextAction(new AttackAction(entity, prey));
        return true;
    }
}
