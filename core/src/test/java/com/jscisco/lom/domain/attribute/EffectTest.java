package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EffectTest {

    private class TestEffect extends Effect {
        @Override
        public void apply(AttributeSet attributeSet) {

        }
    }

    @Test
    public void whenDurationIsExpired_thenEffectIsExpired() {
        Effect effect = new TestEffect();
        effect.duration = Duration.turns(0);

        assertThat(effect.isExpired()).isTrue();
    }

    @Test
    public void whenDurationIsPermanent_thenItNeverExpires() {
        Effect effect = new TestEffect();
        effect.duration = Duration.permanent();
        effect.duration.decrementDuration();

        assertThat(effect.isExpired()).isFalse();
    }

}
