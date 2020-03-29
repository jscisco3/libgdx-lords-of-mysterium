package com.jscisco.lom.action;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AttackAction extends AbstractAction {

    private static final Logger logger = LoggerFactory.getLogger(AttackAction.class);

    private final Entity defender;

    public AttackAction(Entity source, Entity defender) {
        super(source);
        this.defender = defender;
    }

    @Override
    public ActionResult invoke() {
        List<Attack> attacks = source.getAttacks();
        attacks.forEach(a -> a.getDamages().forEach(d -> this.defender.getHealth().reduce(d.getDamage())));
        if (this.defender.getHealth().shouldBeDestroyed()) {
            logger.info("{} has been destroyed!", this.defender);
            defender.getStage().removeEntity(defender);
        }
        return ActionResult.success();
    }
}
