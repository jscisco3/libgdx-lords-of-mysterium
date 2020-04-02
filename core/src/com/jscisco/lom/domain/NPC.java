package com.jscisco.lom.domain;

public class NPC extends Entity {
    public NPC(EntityId id, EntityName name) {
        super(id, name, new Position(0, 0));
    }
}
