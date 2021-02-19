package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private final Name name;
    private final Description description;
    private int baseValue = 0;
    private List<AttributeEffect> effects = new ArrayList<>();

    public Attribute(Name name, Description description, int baseValue) {
        this.name = name;
        this.description = description;
        this.baseValue = baseValue;
    }

    public int getBaseValue() {
        return this.baseValue;
    }

    public int getValue() {
        return applyEffects();
    }

    private int applyEffects() {
        return applyAdders(applyMultipliers(this.baseValue));
    }

    private int applyMultipliers(int value) {
        int temp = value;
        for (AttributeEffect effect : this.effects) {
            if (effect.getOperator().equals(AttributeOperator.MULTIPLY)) {
                temp = effect.apply(temp);
            }
        }
        return temp;
    }

    private int applyAdders(int value) {
        int temp = value;
        for (AttributeEffect effect : this.effects) {
            if (effect.getOperator().equals(AttributeOperator.ADD)) {
                temp = effect.apply(temp);
            }
        }
        return temp;
    }

    public void addEffect(AttributeEffect effect) {
        this.effects.add(effect);
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public List<AttributeEffect> getEffects() {
        return effects;
    }
}
