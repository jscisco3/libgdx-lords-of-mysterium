package com.jscisco.lom.domain;

public class TestEntity extends Entity {
    public TestEntity(EntityId id, EntityName name) {
        super(id, name, new Position(0, 0));
    }
}