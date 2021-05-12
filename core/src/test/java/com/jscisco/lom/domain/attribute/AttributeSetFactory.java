package com.jscisco.lom.domain.attribute;

public class AttributeSetFactory {

    public static AttributeSet getAttributeSet() {
        AttributeSet attributeSet = new AttributeSet();
        attributeSet.initialize();
        attributeSet.applyBaseValueModifier(new AttributeModifier()
                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                .withOperator(Attribute.Operator.OVERRIDE)
                .withMagnitude(200f)
        );
        attributeSet.applyBaseValueModifier(new AttributeModifier()
                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                .withOperator(Attribute.Operator.OVERRIDE)
                .withMagnitude(100f)
        );
        return attributeSet;
    }

}
