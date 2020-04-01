package com.jscisco.lom.domain;

import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class MoveActionTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cannotCreateMoveActionIfNewPositionIsNull() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                new MoveAction(EntityFactory.testEntity(), null));
    }

    @Test
    public void invokingMoveActionMovesTheEntity() {
        Entity e = EntityFactory.testEntity();
        MoveAction action = new MoveAction(e, new Position(1, 1));
        action.invoke();
        Assertions.assertThat(e.position).isEqualTo(new Position(1, 1));
    }

    @Test
    public void invokingMoveActionReturnsSuccessResult() {
        MoveAction action = new MoveAction(EntityFactory.testEntity(), new Position(1, 1));
        ActionResult result = action.invoke();
        Assertions.assertThat(result.succeeded()).isTrue();
    }

}
