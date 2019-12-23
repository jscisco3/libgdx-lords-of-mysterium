package com.jscisco.lom.effect;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class TestTimedEffect {

    @Spy
    TimedEffect effect = new TimedEffect(5) {
    };

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenTickingTimerIsReduced() {
        effect.tick();
        Mockito.verify(effect).tick();
        Assertions.assertThat(effect.timeRemaining()).isEqualTo(4);
    }

    @Test
    void whenTickingReducesTimeToZeroItIsDestroyed() {
        effect.tick();
        effect.tick();
        effect.tick();
        effect.tick();
        Assertions.assertThat(effect.timeRemaining()).isEqualTo(1);
        effect.tick();
        Mockito.verify(effect).destroy();
    }
}
