package com.jscisco.lom.domain;

public class Player extends Entity {
    public Player(EntityId id, EntityName name) {
        super(id, name, new Position(0, 0));
    }
}
