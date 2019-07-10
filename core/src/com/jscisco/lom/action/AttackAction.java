package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;

public class AttackAction extends Action {

    private Entity defender;

    public AttackAction(Entity source, Entity defender) {
        super(source);
        this.defender = defender;
    }

    @Override
    public ActionResult invoke() {
        this.defender.getHealth().damage(10);

        if (this.defender.getHealth().shouldBeDestroyed()) {
            defender.getStage().removeEntity(defender);
        }

        return ActionResult.success();
    }
}
