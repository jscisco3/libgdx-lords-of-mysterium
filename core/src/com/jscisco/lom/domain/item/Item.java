package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.Position;

import java.util.Objects;

public class Item {
    ItemName name;
    Position position;
    Equippable equippable;
    int quantity;

    public Item() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isEquippable() {
        return Objects.isNull(equippable);
    }

}
