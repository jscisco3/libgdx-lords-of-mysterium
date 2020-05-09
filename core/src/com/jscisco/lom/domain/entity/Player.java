package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.component.Inventory;

public class Player extends AbstractEntity {

    public Player(EntityName name) {
        this.name = name;
        this.inventory = new Inventory();
    }

}
