package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeSetTest {

    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = new AttributeSet();
        this.attributeSet.initialize();
    }

    @Test
    public void whenHealthIsIncreasedByInstantEffect_thenItCannotBeHigherThanMaxHealth() {
        Effect effect = new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                        .withMagnitude(1000f)
                        .withOperator(Attribute.Operator.ADD));

        float maxHealthValue = attributeSet.getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH);

        effect.apply(this.attributeSet);

        assertThat(this.attributeSet.getAttributeValue(AttributeSet.AttributeDefinition.HEALTH)).isEqualTo(maxHealthValue);
        assertThat(this.attributeSet.getHealth().getBaseValue()).isEqualTo(maxHealthValue);
    }
}
