package com.jscisco.lom.combat;

public class Damage {
    private DamageType type;
    private int damage;

    public Damage(DamageType type, int damage) {
        this.type = type;
        this.damage = damage;
    }

    public DamageType getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

}