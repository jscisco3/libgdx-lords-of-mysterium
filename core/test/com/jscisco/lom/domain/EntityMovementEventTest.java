package com.jscisco.lom.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityMovementEventTest {

    @Test
    public void processingEntityMovementEventMovesTheEntity() {
        Entity entity = new TestEntity(new EntityId(12345L), new EntityName("test entity"));
        Position newPosition = new Position(1, 1);
        EntityMovementEvent event = new EntityMovementEvent(entity, newPosition);
        event.process();

        Assertions.assertThat(entity.position).isEqualTo(newPosition);
    }

    @Test
    public void entityMovementEventCannotBeCreatedIfEntityIsNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new EntityMovementEvent(null, new Position(1, 1)));
    }

    @Test
    public void entityMovementEventCannotBeCreatedIfPositionIsNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new EntityMovementEvent(new TestEntity(
                        new EntityId(12345L), new EntityName("test entity")),
                        null));
    }

}
