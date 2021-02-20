package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DurationTest {

    @Test
    public void tickShouldDecreaseDuration() {
        Duration d = Duration.turns(10);
        d.tick();

        assertThat(d.turnsRemaining()).isEqualTo(9);
    }

    @Test
    public void durationShouldBeExpiredIfNotPermanentAndTurnsLeftIsZero() {
        Duration d = Duration.turns(1);
        d.tick();

        assertThat(d.expired()).isTrue();
    }

}
