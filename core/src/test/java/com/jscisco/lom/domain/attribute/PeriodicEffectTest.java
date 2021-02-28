package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PeriodicEffectTest {

    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = AttributeSetFactory.getAttributeSet();
    }

    @Test
    public void whenPeriodicEffectTicks_thenItModifiesTheBaseValue() {
        Effect periodicEffect = new PeriodicEffect()
                .withDuration(Duration.turns(100))
                .addModifier(new AttributeModifier()
                        .forAttribute(attributeSet.getHealth())
                        .withMagnitude(-10f)
                        .withOperator(Attribute.Operator.ADD));

        periodicEffect.apply(attributeSet);
        periodicEffect.tick();
        assertThat(attributeSet.getHealth().getBaseValue()).isEqualTo(90f);
    }
}
