package com.jscisco.lom.domain.attribute;

import javax.persistence.Entity;

@Entity
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