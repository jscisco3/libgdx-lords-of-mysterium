package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    public enum AttributeType {
        HEALTH("Health", "Current health"),
        MAX_HEALTH("Maximum Health", "Maximum health");

        public final Name name;
        public final Description description;

        AttributeType(String name, String description) {
            this.name = Name.of(name);
            this.description = Description.of(description);
        }
    }

    public enum Operator {
        ADD,
        MULTIPLY
    }

    private float baseValue = 0f;
    private final AttributeType type;
    private final List<AttributeModifier> modifiers = new ArrayList<>();

    public Attribute(AttributeType type) {
        this.type = type;
    }

    public Attribute(AttributeType type, float baseValue) {
        this.type = type;
        this.baseValue = baseValue;
    }

    public float getValue() {
        return applyAdders(applyMultipliers(baseValue));
    }

    public float applyMultipliers(float value) {
        return value;
    }

    public float applyAdders(float value) {
        return value;
    }

    public Name getName() {
        return type.name;
    }

    public Description getDescription() {
        return type.description;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public void addModifier(AttributeModifier modifier) {
        this.modifiers.add(modifier);
    }

    public AttributeType getType() {
        return type;
    }

    public void removeModifier(AttributeModifier modifier) {
        this.modifiers.remove(modifier);
    }

}
