package com.jscisco.lom.domain.attribute;

public class AttributeEffect {
    // Duration
    private Duration duration;
    // Magnitude
    private int magnitude;
    // Operation
    private AttributeOperator operator;

    public AttributeEffect() {
    }

    public AttributeEffect withDuration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public AttributeEffect withMagnitude(int magnitude) {
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

    public int apply(int value) {
        switch (operator) {
            case ADD:
                return value + magnitude;
            case MULTIPLY:
                return value * magnitude;
        }
        // Maybe throw exception
        return value;
    }

}
