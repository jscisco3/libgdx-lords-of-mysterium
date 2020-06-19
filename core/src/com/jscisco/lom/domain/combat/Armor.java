package com.jscisco.lom.domain.combat;

public class Armor {
    int armor;

    private Armor(int armor) {
        this.armor = armor;
    }

    public static Armor of(int armor) {
        return new Armor(armor);
    }

}
