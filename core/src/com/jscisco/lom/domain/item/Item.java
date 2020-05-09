package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.Position;

public class Item {
    ItemName name;
    Position position;
    int quantity;

    public Item() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
