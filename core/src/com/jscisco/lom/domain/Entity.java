package com.jscisco.lom.domain;

public abstract class Entity {

    EntityId id;
    EntityName name;
    Position position;

    // Statistics
    Health health;

    public Entity(EntityId id, EntityName name) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.id = id;
        this.name = name;
    }
}
