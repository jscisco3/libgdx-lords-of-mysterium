package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.action.AttackAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttackTargetAction extends GOAPAction {

    private static final Logger logger = LoggerFactory.getLogger(AttackTargetAction.class);

    public AttackTargetAction() {
        addPrecondition(GOAPGoal.HAS_TARGET, true);
        addEffect(GOAPGoal.ATTACK_TARGET, true);
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
        this.target = (Position) entity.getWorldState().get(GOAPGoal.TARGET);
        logger.info("Attacking entity at {}", this.target);
        Entity defender = entity.getStage().getEntityAtPosition(this.target);
        if (defender != null) {
            entity.setNextAction(new AttackAction(entity, defender));
            return true;
        }
        return false;
    }
}
