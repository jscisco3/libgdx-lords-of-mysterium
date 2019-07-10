package com.jscisco.lom.items;

public class ItemType {

    private String name;
    private String description;
    private int value;

    public ItemType(String name, String description, int value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
