package com.jscisco.lom.domain.attribute;

public class AttributeModifier {
    protected Attribute.AttributeType attributeType;
    protected float magnitude;
    protected Attribute.Operator operator;

    public AttributeModifier() {

    }

    public AttributeModifier forType(Attribute.AttributeType attributeType) {
        this.attributeType = attributeType;
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

    public Attribute.AttributeType getAttributeType() {
        return attributeType;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public Attribute.Operator getOperator() {
        return operator;
    }
}
