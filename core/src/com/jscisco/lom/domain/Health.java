package com.jscisco.lom.domain;

import java.util.Objects;

public final class Health {

    private int hp;
    private int maxHP;

    public Health(int maxHP) {
        this.hp = maxHP;
        this.maxHP = maxHP;
    }

    public int maxHp() {
        return maxHP;
    }

    public int hp() {
        return hp;
    }

    public boolean shouldBeDestroyed() {
        return this.hp <= 0;
    }

    public void reduce(int damage) {
        this.hp -= damage;
    }

    public void increase(int amount) {
        this.hp += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Health health = (Health) o;
        return hp == health.hp &&
                maxHP == health.maxHP;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hp, maxHP);
    }

    @Override
    public String toString() {
        return "Health{" +
                "hp=" + hp +
                ", maxHP=" + maxHP +
                '}';
    }
}
