package com.jscisco.lom.domain.attribute;

public class PeriodicEffect extends Effect {

    @Override
    public void apply(AttributeSet attributeSet) {
        this.tick();
        for (AttributeModifier modifier : modifiers) {
            attributeSet.applyBaseValueModifier(modifier);
        }
    }
}
