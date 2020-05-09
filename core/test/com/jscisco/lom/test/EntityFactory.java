package com.jscisco.lom.test;

import com.jscisco.lom.shelf.domain.*;

public class EntityFactory {

    public static Entity testEntity() {
        return new TestEntity(new EntityId(12345L), new EntityName("test name"));
    }

    public static Entity testEntityWithHealth() {
        return new TestEntity(new EntityId(12345L), new EntityName("test name")).withHealth(new Health(100));
    }

}
