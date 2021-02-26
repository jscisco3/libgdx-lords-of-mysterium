package com.jscisco.lom.domain.attribute;

import java.util.ArrayList;
import java.util.List;

public abstract class Effect {
    protected Duration duration;
    protected List<AttributeModifier> modifiers = new ArrayList<>();
    protected List<Tag> grantedTags = new ArrayList<>();

    public List<AttributeModifier> getModifiers() {
        return modifiers;
    }
}
