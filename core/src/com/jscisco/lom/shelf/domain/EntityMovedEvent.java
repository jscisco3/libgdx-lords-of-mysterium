package com.jscisco.lom.shelf.domain;

public class EntityMovedEvent extends Event {

    private Entity entity;
    private Position position;

    public EntityMovedEvent(Entity entity, Position position) {
        super(EventType.ENTITY_MOVED_EVENT);
        this.entity = entity;
        this.position = position;
    }

    public Entity entity() {
        return this.entity;
    }

    public Position position() {
        return this.position;
    }

    @Override
    public String toString() {
        return "EntityMovedEvent{" +
                "entity=" + entity +
                ", position=" + position +
                '}';
    }
}
