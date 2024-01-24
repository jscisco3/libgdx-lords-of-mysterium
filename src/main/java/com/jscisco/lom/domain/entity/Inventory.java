package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Inventory {

    List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        items.sort(Comparator.comparing(Item::getItemType));
        return items;
    }
}
