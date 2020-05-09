package com.jscisco.lom.shelf.entity;

public interface DeathStrategy {
    default void die(Entity entity) {
        entity.getStage().removeEntity(entity);
    }
}
