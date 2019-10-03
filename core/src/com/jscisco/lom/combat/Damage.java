package com.jscisco.lom.combat;

public class Damage {
    private DamageType type;
    private int magnitude;

    public Damage(DamageType type, int magnitude) {
        this.type = type;
        this.magnitude = magnitude;
    }

    public DamageType getType() {
        return type;
    }

    public int getMagnitude() {
        return magnitude;
    }
}
