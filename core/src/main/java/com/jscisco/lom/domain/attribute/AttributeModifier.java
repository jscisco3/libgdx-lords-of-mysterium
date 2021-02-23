package com.jscisco.lom.domain.attribute;

public abstract class AttributeModifier {
    // Attribute it effects
    protected Attribute attribute;
    // Magnitude
    protected float magnitude;
    // Operation
    protected AttributeOperator operator;

    public AttributeModifier() {
    }

    public AttributeModifier forAttribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public AttributeModifier withMagnitude(float magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public AttributeModifier withOperator(AttributeOperator operator) {
        this.operator = operator;
        return this;
    }

    public AttributeOperator getOperator() {
        return operator;
    }

    public abstract void apply();

}
