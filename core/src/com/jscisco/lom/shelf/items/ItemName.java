package com.jscisco.lom.shelf.items;

import java.util.Objects;

public class ItemName {
    private String name;

    public ItemName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemName)) return false;
        ItemName itemName = (ItemName) o;
        return name.equals(itemName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
