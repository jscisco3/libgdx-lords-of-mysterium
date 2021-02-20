package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeEffectTest {

    @Test
    public void whenDurationIsExpired_thenAttributeEffectIsExpired() {
        AttributeEffect effect = new AttributeEffect()
                .withDuration(Duration.turns(0));

        assertThat(effect.expired()).isTrue();
    }

}
