package com.jscisco.lom.domain.combat;

import java.util.Objects;

public class Damage {
    public enum Type {
        PHYSICAL,
        FIRE,
        LIGHTNING,
        COLD
    }

    int damage;
    Type type;

    public Damage(int damage, Type type) {
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Damage)) return false;
        Damage damage1 = (Damage) o;
        return damage == damage1.damage &&
                type == damage1.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, type);
    }
}
