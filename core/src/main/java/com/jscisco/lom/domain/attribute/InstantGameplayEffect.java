package com.jscisco.lom.domain.attribute;

public class InstantGameplayEffect extends GameplayEffect {

    @Override
    public void apply() {
        for (AttributeModifier modifier : modifiers) {
            switch (modifier.operator) {
                case ADD:
                    modifier.attribute.setBaseValue(modifier.attribute.getBaseValue() + modifier.magnitude);
                    break;
                case MULTIPLY:
                    modifier.attribute.setBaseValue(modifier.attribute.getBaseValue() * modifier.magnitude);
            }
        }
    }
}
