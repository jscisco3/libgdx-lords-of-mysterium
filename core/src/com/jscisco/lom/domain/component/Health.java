package com.jscisco.lom.domain.component;

public class Health {

    int hp;
    int maxHp;

    public Health(int hp) {
        this.hp = hp;
        this.maxHp = hp;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
}
