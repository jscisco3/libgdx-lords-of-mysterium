package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.entity.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    List<GameObject> inventory = new ArrayList<>();

    public Inventory() {
    }

    public Inventory(List<GameObject> inventory) {
        this.inventory = inventory;
    }

    public void addItem(GameObject item) {
        this.inventory.add(item);
    }

    public GameObject removeItem(int index) {
        return this.inventory.remove(index);
    }

    public List<GameObject> items() {
        return this.inventory;
    }
}
