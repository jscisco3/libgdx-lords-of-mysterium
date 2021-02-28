package com.jscisco.lom.domain.attribute;

public class AttributeModifier {
    protected Attribute attribute;
    protected float magnitude;
    protected Attribute.Operator operator;

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

    public AttributeModifier withOperator(Attribute.Operator operator) {
        this.operator = operator;
        return this;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public Attribute.Operator getOperator() {
        return operator;
    }
}
