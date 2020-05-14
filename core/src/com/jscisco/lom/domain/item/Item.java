package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.component.Equipment;

import java.util.Objects;

public class Item {
    ItemName name;
    Position position;
    Equippable equippable;
    Weapon weapon;

    Item() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isEquippable() {
        return !Objects.isNull(equippable);
    }

    public Equippable equippable() {
        return this.equippable;
    }

    public String getName() {
        return name.name;
    }
}
