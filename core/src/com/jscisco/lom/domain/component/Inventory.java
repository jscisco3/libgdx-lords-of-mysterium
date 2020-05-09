package com.jscisco.lom.domain.component;

import com.jscisco.lom.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<Item> inventory = new ArrayList<>();

    public Inventory() {
    }

    public Inventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public Item removeItem(int index) {
        return this.inventory.remove(index);
    }

    public List<Item> items() {
        return this.inventory;
    }
}
