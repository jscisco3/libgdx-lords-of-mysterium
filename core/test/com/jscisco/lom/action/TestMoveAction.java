package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class TestMoveAction {
    private Entity entity;

    @Mock
    private Stage stage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.entity = new Entity() {};
        this.entity.setPosition(new Position(0, 0));
        this.entity.setStage(stage);
    }

    @Test
    void moveActionShouldSucceedIfCanWalkInNewPosition() {
        Mockito.when(stage.getEntityAtPosition(any())).thenReturn(null);
        Mockito.when(stage.terrainIsWalkableAtPosition(any())).thenReturn(true);

        MoveAction action = new MoveAction(this.entity, 1, 1);
        ActionResult result = action.invoke();
        assertTrue(result.succeeded());
        assertNull(result.getAlternative());
        assertEquals(new Position(1, 1), this.entity.getPosition());
    }

    @Test
    void moveActionShouldReturnAlternateAttackActionIfEntityInNewPosition() {
        Entity defender = new Entity() {};
        Mockito.when(stage.getEntityAtPosition(any())).thenReturn(defender);

        MoveAction action = new MoveAction(this.entity, 1, 1);
        ActionResult result = action.invoke();

        assertTrue(result.succeeded());
        assertEquals(AttackAction.class, result.getAlternative().getClass());
    }
}
