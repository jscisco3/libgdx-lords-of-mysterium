package com.jscisco.lom.domain.attribute;

public class AttributeFactory {

    public static Attribute testAttribute() {
        return testAttribute(10f);
    }


    public static Attribute testAttribute(float value) {
        return new Attribute(Attribute.AttributeType.HEALTH, value);
    }

}
