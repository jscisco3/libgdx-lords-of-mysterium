package com.jscisco.lom.domain.attribute;

public class InstantEffect extends Effect {

    public InstantEffect() {
        this.duration = null;
    }

    @Override
    public void apply(AttributeSet attributeSet) {
        for (AttributeModifier modifier : modifiers) {
            attributeSet.applyBaseValueModifier(modifier);
        }
    }
}