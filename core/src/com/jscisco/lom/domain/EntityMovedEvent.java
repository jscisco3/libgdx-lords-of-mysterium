package com.jscisco.lom.domain;

public class EntityMovedEvent extends Event {

    private Entity entity;
    private Position position;

    public EntityMovedEvent(Entity entity, Position position) {
        this.entity = entity;
        this.position = position;
    }

    public Entity entity() {
        return this.entity;
    }

    public Position position() {
        return this.position;
    }
}
