package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InstantEffectTest {

    private Effect effect;
    private AttributeSet attributeSet;

    @BeforeEach
    public void setup() {
        this.attributeSet = new AttributeSet();
    }

    @Test
    public void whenInstantEffectIsApplied_thenTheAttributesBaseValueIsUpdated() {

        Attribute testAttribute = attributeSet.getMaxHealth();

        effect = new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(testAttribute)
                        .withMagnitude(10f)
                        .withOperator(Attribute.Operator.ADD));

        float initialValue = attributeSet.getAttributeValue(testAttribute);
        float expectedValue = initialValue + 10f;
        effect.apply(attributeSet);

        assertThat(attributeSet.getAttributeValue(testAttribute)).isEqualTo(expectedValue);
    }
}
