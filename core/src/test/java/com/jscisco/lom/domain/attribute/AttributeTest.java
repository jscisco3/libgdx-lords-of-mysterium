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

        AttributeEffect effect = new AttributeEffect()
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

        AttributeEffect effect = new AttributeEffect()
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

        AttributeEffect add = new AttributeEffect()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.ADD);

        AttributeEffect multiply = new AttributeEffect()
                .withDuration(Duration.permanent())
                .withMagnitude(10)
                .withOperator(AttributeOperator.MULTIPLY);

        attribute.addEffect(add);
        attribute.addEffect(multiply);

        int expectedValue = 110;
        assertThat(attribute.getValue()).isEqualTo(expectedValue);
    }

}
