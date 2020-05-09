package com.jscisco.lom.shelf.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActionTest {

    @Test
    public void actionCanOnlyBeCreatedIfEntityIsNotNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new TestAction(null));
    }
}
