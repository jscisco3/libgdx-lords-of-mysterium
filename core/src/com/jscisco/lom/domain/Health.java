package com.jscisco.lom.domain;

public class Health {

    int hp;
    int maxHp;

    public Health(int hp) {
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decrease(int hp) {
        this.hp -= hp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
