package com.jscisco.lom.domain;

public class EntityMovementEvent extends Event {
    Entity entity;
    Position newPosition;

    public EntityMovementEvent(Entity entity, Position newPosition) {
        if (entity == null || newPosition == null) {
            throw new IllegalArgumentException();
        }
        this.entity = entity;
        this.newPosition = newPosition;
    }

    @Override
    public void process() {
        entity.move(newPosition);
    }
}
