package com.jscisco.lom.shelf.domain;

import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class AttackActionTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void attackActionShouldEnqueueAHealthLostEvent() {
        Entity attacker = EntityFactory.testEntityWithHealth();
        Entity defender = EntityFactory.testEntityWithHealth();
        int startingHealth = defender.health.hp();
        AttackAction action = new AttackAction(attacker, defender);
        action.invoke();
        Assertions.assertThat(defender.health.hp()).isLessThan(startingHealth);
    }

}
