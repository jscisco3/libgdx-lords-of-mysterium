package com.jscisco.lom.domain.attribute;

import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;

import static com.jscisco.lom.domain.MathUtils.clamp;

/**
 * Responsible for maintaining relevant attributes
 */
public class AttributeSet {

    public enum AttributeDefinition {
        HEALTH,
        MAX_HEALTH,
        LIGHT_RADIUS
    }

    private final Attribute health;
    private final Attribute maxHealth;
    private final Attribute lightRadius;

    public AttributeSet() {
        health = new Attribute(Name.of("Health"), Description.of("Current health"));
        maxHealth = new Attribute(Name.of("Max Health"), Description.of("Maximum health"));
        lightRadius = new Attribute(Name.of("Light Radius"), Description.of("Light radius"));
    }

    public Attribute getHealth() {
        return health;
    }

    public Attribute getMaxHealth() {
        return maxHealth;
    }

    public Attribute getLightRadius() {
        return lightRadius;
    }

    public void applyBaseValueModifier(AttributeModifier modifier) {
        float newValue = 0f;

        switch (modifier.operator) {
            case MULTIPLY:
                newValue = modifier.getAttribute().getBaseValue() * modifier.getMagnitude();
                break;
            case ADD:
                newValue = modifier.getAttribute().getBaseValue() + modifier.getMagnitude();
                break;
            case OVERRIDE:
                newValue = modifier.getMagnitude();
                break;
        }

        // Health can never permanently be greater than the max health.
        if (modifier.getAttribute().equals(this.health)) {
            newValue = (clamp(0f, this.maxHealth.getValue(), newValue));
        }
        modifier.getAttribute().setBaseValue(newValue);
    }

    public float getAttributeValue(Attribute attribute) {
        float attributeValue = attribute.getValue();

        if (attribute.equals(this.health)) {
            attributeValue = clamp(this.health.getValue(), this.maxHealth.getValue(), attributeValue);
        }
        return attributeValue;
    }

    public float getAttributeValue(AttributeDefinition attribute) {
        switch (attribute) {
            case HEALTH:
                return getAttributeValue(health);
            case MAX_HEALTH:
                return getAttributeValue(maxHealth);
        }
        throw new IllegalArgumentException();
    }

    /**
     * @param modifier
     */
    public void applyTemporaryModifier(AttributeModifier modifier) {
        modifier.getAttribute().addModifier(modifier);
    }
}
