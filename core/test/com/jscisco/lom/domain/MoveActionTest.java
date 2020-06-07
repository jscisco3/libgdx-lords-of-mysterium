package com.jscisco.lom.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveActionTest {

    GameObject entity;

    @BeforeEach
    void setUp() {
        entity = Entity.player(new EntityName("Test"), new Health(100));
        entity.position = Position.of(0, 0);
    }

    /**
     * When a move action is invoked for a particular entity
     * The entity is moved to the new position
     */
    @Test
    public void successfulMoveAction() {
        MoveAction action = new MoveAction(entity, Position.of(1, 1));
        ActionResult result = action.invoke();
        assertThat(result.succeeded).isTrue();
        assertThat(result.alternative).isNull();
        assertThat(entity.position).isEqualTo(Position.of(1, 1));
    }


}
