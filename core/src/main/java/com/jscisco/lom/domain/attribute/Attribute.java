package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private final Name name;
    private final Description description;
    private float baseValue = 0f;
    List<AttributeModifier> modifiers = new ArrayList<>();

    public Attribute(Name name, Description description, float baseValue) {
        this.name = name;
        this.description = description;
        this.baseValue = baseValue;
    }

    public float getCurrentValue() {
        return applyEffects();
    }

    private float applyEffects() {
        return applyAdders(applyMultipliers(this.baseValue));
    }

    private float applyMultipliers(float value) {
        float temp = value;
        for (AttributeModifier modifier : this.modifiers) {
            if (modifier.getOperator().equals(AttributeOperator.MULTIPLY)) {
                temp = modifier.apply(temp);
            }
        }
        return temp;
    }

    private float applyAdders(float value) {
        float temp = value;
        for (AttributeModifier effect : this.modifiers) {
            if (effect.getOperator().equals(AttributeOperator.ADD)) {
                temp = effect.apply(temp);
            }
        }
        return temp;
    }

    public void addEffect(AttributeModifier effect) {
        this.modifiers.add(effect);
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public List<AttributeModifier> getModifiers() {
        return modifiers;
    }

    public void removeModifier(AttributeModifier modifier) {
        this.modifiers.remove(modifier);
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }
}
