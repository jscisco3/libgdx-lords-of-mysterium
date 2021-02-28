package com.jscisco.lom.domain.attribute;

public class AttributeSetFactory {

    public static AttributeSet getAttributeSet() {
        AttributeSet attributeSet = new AttributeSet();
        attributeSet.applyBaseValueModifier(new AttributeModifier()
                .forAttribute(attributeSet.getMaxHealth())
                .withOperator(Attribute.Operator.OVERRIDE)
                .withMagnitude(200f)
        );
        attributeSet.applyBaseValueModifier(new AttributeModifier()
                .forAttribute(attributeSet.getHealth())
                .withOperator(Attribute.Operator.OVERRIDE)
                .withMagnitude(100f)
        );
        return attributeSet;
    }

}
