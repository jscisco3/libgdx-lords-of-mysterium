package com.jscisco.lom.combat;

import java.util.Random;

public class Damage {
    private DamageType type;
    private int minPower;
    private int maxPower;

    public Damage(DamageType type, int minPower, int maxPower) {
        this.type = type;
        this.minPower = minPower;
        this.maxPower = maxPower;
    }

    public DamageType getType() {
        return type;
    }

    public int getMagnitude() {
        Random random = new Random();
        return (random.nextInt(maxPower - minPower) + minPower + 1);
    }
}