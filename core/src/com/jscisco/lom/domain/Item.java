package com.jscisco.lom.domain;

import java.util.Objects;

public class Item extends GameObject {
    Equippable equippable;
    Weapon weapon;

    private Item() {
    }

    public static Item nonEquippable(EntityName name) {
        Item item = new Item();
        item.name = name;
        return item;
    }

    public static Item equipment(EntityName name, Equippable equippable) {
        Item item = new Item();
        item.name = name;
        item.equippable = equippable;
        return item;
    }

    public boolean isEquippable() {
        return !Objects.isNull(equippable);
    }

    public Equippable equippable() {
        return this.equippable;
    }

}
