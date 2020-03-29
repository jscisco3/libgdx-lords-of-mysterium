package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;

public class AttackAction extends Action {

    private Entity defender;

    public AttackAction(EventProcessor eventProcessor, Entity source, Entity defender) {
        super(eventProcessor, source);
        if (defender == null) {
            throw new IllegalArgumentException("Defender is required when creating an attack action");
        }
        this.defender = defender;
    }

    @Override
    public ActionResult invoke() {
        eventProcessor.enqueue(new HealthLostEvent(defender, 10));
        return ActionResult.success();
    }
}
