package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;
import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MoveActionTest {

    @Mock
    EventProcessor processor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cannotCreateMoveActionIfNewPositionIsNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new MoveAction(this.processor, EntityFactory.testEntity(), null));
    }

    @Test
    public void invokingMoveActionAddsEntityMovementEventToEventProcessor() {
        MoveAction action = new MoveAction(this.processor, EntityFactory.testEntity(), new Position(1, 1));
        action.invoke();
        Mockito.verify(processor).enqueue(Mockito.any(EntityMovementEvent.class));
    }

    @Test
    public void invokingMoveActionReturnsSuccessResult() {
        MoveAction action = new MoveAction(this.processor, EntityFactory.testEntity(), new Position(1, 1));
        ActionResult result = action.invoke();
        Assertions.assertThat(result.succeeded()).isTrue();
    }

}
