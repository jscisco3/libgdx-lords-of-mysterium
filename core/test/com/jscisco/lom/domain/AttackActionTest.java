package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;
import com.jscisco.lom.test.EntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class AttackActionTest {

    @Mock
    EventProcessor eventProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void attackActionShouldEnqueueAHealthLostEvent() {
        Entity attacker = EntityFactory.testEntityWithHealth();
        Entity defender = EntityFactory.testEntityWithHealth();
        AttackAction action = new AttackAction(eventProcessor, attacker, defender);
        action.invoke();
        Mockito.verify(eventProcessor).enqueue(any(HealthLostEvent.class));
    }

}
