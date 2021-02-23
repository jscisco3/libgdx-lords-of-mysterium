package com.jscisco.lom.domain.attribute;

public class PeriodicGameplayEffect extends GameplayEffect {

    private Duration duration;

    @Override
    public void apply() {
        for (AttributeModifier modifier : modifiers) {
            if (duration.isPermanent()) {
                switch (modifier.operator) {
                    case ADD:
                        modifier.attribute.setBaseValue(modifier.attribute.getBaseValue() + modifier.magnitude);
                        break;
                    case MULTIPLY:
                        modifier.attribute.setBaseValue(attribute.getBaseValue() * magnitude);
                }
            } else {
                switch (operator) {
                    case ADD:
                        attribute.setBaseValue(attribute.getBaseValue() + magnitude);
                        break;
                    case MULTIPLY:
                        attribute.setBaseValue(attribute.getBaseValue() * magnitude);
                }
                this.duration.tick();
            }
        }
    }
}
