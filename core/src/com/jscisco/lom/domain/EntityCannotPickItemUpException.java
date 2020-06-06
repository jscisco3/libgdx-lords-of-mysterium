package com.jscisco.lom.domain;

public class EntityCannotPickItemUpException extends RuntimeException {
    GameObject entity;
    GameObject item;

    public EntityCannotPickItemUpException(GameObject entity, GameObject item) {
        this.entity = entity;
        this.item = item;
    }
}
