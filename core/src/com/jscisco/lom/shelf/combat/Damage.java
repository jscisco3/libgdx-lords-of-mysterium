package com.jscisco.lom.shelf.combat;

import java.util.Random;

public class Damage {
    private DamageType type;
    private int min;
    private int max;

    public Damage(DamageType type, int min, int max) {
        this.type = type;
        this.min = min;
        this.max = max;
    }

    public DamageType getType() {
        return type;
    }

    public int getDamage() {
        return new Random().nextInt(max - min + 1) + min;
    }

}