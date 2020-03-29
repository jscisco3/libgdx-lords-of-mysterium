package com.jscisco.lom.domain;

import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HealthLostEventTest {

    @Test
    public void healthLostEventShouldDecreaseHealthWhenProcessed() {
        Entity e = EntityFactory.testEntityWithHealth();
        Health health = e.health;
        HealthLostEvent hle = new HealthLostEvent(e, 10);
        hle.process();
        Assertions.assertThat(health.hp()).isEqualTo(90);
    }

}
