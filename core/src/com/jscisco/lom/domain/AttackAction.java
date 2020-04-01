package com.jscisco.lom.domain;

public class AttackAction extends Action {

    private Entity defender;

    public AttackAction(Entity source, Entity defender) {
        super(source);
        if (defender == null) {
            throw new IllegalArgumentException("Defender is required when creating an attack action");
        }
        this.defender = defender;
    }

    @Override
    public ActionResult invoke() {
        this.defender.damage(10);
        return ActionResult.success();
    }
}
