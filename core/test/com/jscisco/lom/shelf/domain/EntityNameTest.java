package com.jscisco.lom.shelf.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityNameTest {

    @Test
    public void validEntityName() {
        EntityName name = new EntityName("Test name");
        Assertions.assertThat(name.name()).isEqualTo("Test name");
    }

    @Test
    public void entityNameCreatedWithNullValue() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new EntityName(null));
    }

    @Test
    public void entityNameCreatedWithEmptyString() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new EntityName(""));
    }

}
