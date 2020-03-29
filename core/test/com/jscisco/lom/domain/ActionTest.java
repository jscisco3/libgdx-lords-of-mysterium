package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ActionTest {

    @Test
    public void actionCanOnlyBeCreatedIfEntityIsNotNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new TestAction(Mockito.mock(EventProcessor.class), null));
    }

    @Test
    public void actionRequiresAnEventProcessor() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new TestAction(null, Mockito.mock(Entity.class)));
    }

}
