package com.jscisco.lom.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegularDeath implements DeathStrategy {

    private static final Logger logger = LoggerFactory.getLogger(RegularDeath.class);

    @Override
    public void die(Entity entity) {
        logger.info("{} has died.", entity.getName().get());
        entity.getStage().removeEntity(entity);
    }
}
