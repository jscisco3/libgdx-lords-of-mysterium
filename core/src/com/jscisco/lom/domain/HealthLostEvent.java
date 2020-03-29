package com.jscisco.lom.domain;

public class HealthLostEvent extends Event {

    private Entity entity;
    private int amount;

    public HealthLostEvent(Entity entity, int amount) {
        if (entity == null || entity.health == null) {
            throw new IllegalArgumentException("Requires an entity with health.");
        }
        this.entity = entity;
        this.amount = amount;
    }

    @Override
    public void process() {
        this.entity.health.reduce(this.amount);
    }
}
