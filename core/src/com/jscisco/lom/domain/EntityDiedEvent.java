package com.jscisco.lom.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityDiedEvent implements Event {

    private static final Logger logger = LoggerFactory.getLogger(EntityDiedEvent.class);

    private Entity entity;

    public EntityDiedEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity entity() {
        return this.entity;
    }

}
