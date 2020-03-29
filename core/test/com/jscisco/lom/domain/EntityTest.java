package com.jscisco.lom.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTest {

    /**
     * Given an entity
     * When it moves
     * Then it should be in the new position
     */
    @Test
    public void testMovementShouldPutEntityInNewPosition() {
        Entity entity = new TestEntity(new EntityId(12345L), new EntityName("Test Name"));
        entity.move(new Position(1, 1));
        Assertions.assertThat(entity.position).isEqualTo(new Position(1, 1));
    }
}
