package com.jscisco.lom.domain;

public class Pools {
    private Pool hp;
    private Pool mp;
    private int experience;
    private int level;

    public Pools(Pool hp, Pool mp) {
        this.hp = hp;
        this.mp = mp;
        this.experience = 0;
        this.level = 1;
    }

    public Pools(Pool hp, Pool mp, int experience, int level) {
        this.hp = hp;
        this.mp = mp;
        this.experience = experience;
        this.level = level;
    }

    public Pool getHp() {
        return hp;
    }

    public Pool getMp() {
        return mp;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void addExperience(int experience) {
        this.experience += experience;
    }

    public void levelUp() {
        this.level += 1;
    }
}
