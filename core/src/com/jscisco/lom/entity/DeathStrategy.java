package com.jscisco.lom.entity;

public interface DeathStrategy {
    default void die(Entity entity) {
        entity.getStage().removeEntity(entity);
    }
}
