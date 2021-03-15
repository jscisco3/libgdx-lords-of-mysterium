package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        return items.add(item);
    }

    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
