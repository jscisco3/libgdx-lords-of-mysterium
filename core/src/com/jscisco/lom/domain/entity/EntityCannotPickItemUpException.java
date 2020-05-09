package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.item.Item;

public class EntityCannotPickItemUpException extends RuntimeException {
    AbstractEntity entity;
    Item item;

    public EntityCannotPickItemUpException(AbstractEntity entity, Item item) {
        this.entity = entity;
        this.item = item;
    }
}
