package com.jscisco.lom.domain.attribute;

public class AttributeEffect {
    // Duration
    private Duration duration;
    // Magnitude
    private float magnitude;
    // Operation
    private AttributeOperator operator;

    public AttributeEffect() {
    }

    public AttributeEffect withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public AttributeEffect withMagnitude(float magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public AttributeEffect withOperator(AttributeOperator operator) {
        this.operator = operator;
        return this;
    }

    public AttributeOperator getOperator() {
        return operator;
    }

    public float apply(float value) {
        switch (operator) {
            case ADD:
                return value + magnitude;
            case MULTIPLY:
                return value * magnitude;
        }
        // Maybe throw exception
        return value;
    }

    public void tick() {
        this.duration.tick();
    }

    public boolean expired() {
        return this.duration.expired();
    }

}
