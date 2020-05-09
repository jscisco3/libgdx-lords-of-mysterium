package com.jscisco.lom.shelf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityDiedEvent extends Event {

    private static final Logger logger = LoggerFactory.getLogger(EntityDiedEvent.class);

    private Entity entity;

    public EntityDiedEvent(Entity entity) {
        super(EventType.ENTITY_DIED_EVENT);
        this.entity = entity;
    }

    public Entity entity() {
        return this.entity;
    }

}
