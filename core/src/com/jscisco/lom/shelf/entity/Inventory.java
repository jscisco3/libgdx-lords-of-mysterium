package com.jscisco.lom.shelf.entity;

import com.jscisco.lom.shelf.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        return this.items.add(item);
    }

    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }
}
