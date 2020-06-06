package com.jscisco.lom.domain;

import java.util.Objects;

public class Item {
    Equippable equippable;
    Weapon weapon;

    Item() {
    }

    public boolean isEquippable() {
        return !Objects.isNull(equippable);
    }

    public Equippable equippable() {
        return this.equippable;
    }

}
