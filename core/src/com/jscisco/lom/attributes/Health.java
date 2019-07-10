package com.jscisco.lom.attributes;

public class Health {

    private int hp;
    private int maxHP;

    public Health(int maxHP) {
        this.hp = maxHP;
        this.maxHP = maxHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean shouldBeDestroyed() {
        return this.hp <= 0;
    }

    public void damage(int damage) {
        this.hp -= damage;
    }
}
