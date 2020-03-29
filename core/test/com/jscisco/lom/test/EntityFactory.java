package com.jscisco.lom.test;

import com.jscisco.lom.domain.Entity;
import com.jscisco.lom.domain.EntityId;
import com.jscisco.lom.domain.EntityName;
import com.jscisco.lom.domain.TestEntity;

public class EntityFactory {

    public static Entity testEntity() {
        return new TestEntity(new EntityId(12345L), new EntityName("test name"));
    }

}
