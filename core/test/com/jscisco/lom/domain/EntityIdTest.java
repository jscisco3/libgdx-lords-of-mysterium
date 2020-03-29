package com.jscisco.lom.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityIdTest {

    @Test
    public void validEntityIdCanBeCreated() {
        EntityId id = new EntityId(12345L);
        Assertions.assertThat(id.id()).isEqualTo(12345L);
    }

    @Test
    public void entityIdCannotBeCreatedWithNullValue() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new EntityId(null));
    }

}
