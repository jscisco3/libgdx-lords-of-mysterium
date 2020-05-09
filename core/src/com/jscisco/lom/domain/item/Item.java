package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.Position;

public class Item {
    ItemName name;
    Position position;
    int quantity;

    public Item() {
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
