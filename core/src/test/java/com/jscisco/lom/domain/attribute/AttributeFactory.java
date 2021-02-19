package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

public class AttributeFactory {

    public static Attribute testAttribute() {
        return testAttribute(10);
    }


    public static Attribute testAttribute(int value) {
        return new Attribute(Name.of("Test"), Description.of("Test"), value);
    }

}
