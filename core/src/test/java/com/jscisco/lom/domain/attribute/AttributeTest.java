package com.jscisco.lom.domain.attribute;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttributeTest {

    @Test
    public void attributeWithNoModifiers_hasExpectedValue() {
        int expectedValue = 10;
        Attribute attribute = AttributeFactory.testAttribute(expectedValue);

        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

    @Test
    public void attributeWithAddModifiers_hasExpectedValue() {
        int startValue = 10;
        Attribute attribute = AttributeFactory.testAttribute(startValue);

        AttributeModifier effect = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.ADD);

        attribute.addEffect(effect);

        int expectedValue = 20;
        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

    @Test
    public void attributeWithMultiplyModifiers_hasExpectedValue() {
        int startValue = 10;
        Attribute attribute = AttributeFactory.testAttribute(startValue);

        AttributeModifier effect = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.MULTIPLY);

        attribute.addEffect(effect);

        int expectedValue = 100;
        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

    @Test
    public void attributeWithBothAddAndMultiplyModifiers_hasExpectedValue() {
        int startValue = 10;
        Attribute attribute = AttributeFactory.testAttribute(startValue);

        AttributeModifier add = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.ADD);

        AttributeModifier multiply = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.MULTIPLY);

        attribute.addEffect(add);
        attribute.addEffect(multiply);

        int expectedValue = 110;
        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

    @Test
    public void attributeApplySubtractionAndDivision_hasExpectedValue() {
        float startValue = 100f;
        Attribute attribute = AttributeFactory.testAttribute(startValue);

        AttributeModifier subtract = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(-10f)
                .withOperator(AttributeOperator.ADD);

        AttributeModifier divide = new AttributeModifier()
                .withDuration(Duration.permanent())
                .withMagnitude(0.5f)
                .withOperator(AttributeOperator.MULTIPLY);

        attribute.addEffect(subtract);
        attribute.addEffect(divide);

        float expectedValue = 40.0f;
        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

}
