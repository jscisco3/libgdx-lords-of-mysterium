package com.jscisco.lom.domain.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GameplayEffect {

    protected final List<AttributeModifier> modifiers;

    public GameplayEffect() {
        this.modifiers = new ArrayList<>();
    }

    public GameplayEffect(List<AttributeModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public void addModifiers(AttributeModifier... modifiers) {
        this.modifiers.addAll(Arrays.asList(modifiers));
    }

    public void removeModifiers(AttributeModifier... modifiers) {
        this.modifiers.removeAll(Arrays.asList(modifiers));
    }

    public abstract void apply();
}
