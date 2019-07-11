package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttackAction extends AbstractAction {

    private static final Logger logger = LoggerFactory.getLogger(AttackAction.class);

    private Entity defender;

    public AttackAction(Entity source, Entity defender) {
        super(source);
        this.defender = defender;
    }

    @Override
    public ActionResult invoke() {
        this.defender.getHealth().damage(10);

        if (this.defender.getHealth().shouldBeDestroyed()) {
            logger.info("{} has been destroyed!", this.defender);
            defender.getStage().removeEntity(defender);
        }

        return ActionResult.success();
    }
}
