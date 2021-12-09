package com.jscisco.lom.domain.event;

import com.jscisco.lom.domain.entity.Entity;

public class EntityRemovedEvent extends Event {

    private final Entity entity;

    public EntityRemovedEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
