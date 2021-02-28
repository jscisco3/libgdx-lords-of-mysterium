package com.jscisco.lom.domain.attribute;

import java.util.ArrayList;
import java.util.List;

public abstract class Effect {
    protected Duration duration;
    protected List<AttributeModifier> modifiers = new ArrayList<>();
    protected List<Tag> grantedTags = new ArrayList<>();


    public Effect withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public Effect addModifier(AttributeModifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public Effect grantTag(Tag tag) {
        this.grantedTags.add(tag);
        return this;
    }

    public List<AttributeModifier> getModifiers() {
        return modifiers;
    }

    public abstract void apply(AttributeSet attributeSet);

    public void tick() {
        if (!this.duration.isPermanent()) {
            this.duration.decrementDuration();
        }
    }

    public boolean isExpired() {
        return this.duration.isExpired();
    }

}
