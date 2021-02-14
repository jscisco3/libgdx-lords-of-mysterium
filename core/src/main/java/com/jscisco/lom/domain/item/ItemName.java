package com.jscisco.lom.domain.item;

import java.util.Objects;

public class ItemName {

    String name;

    private ItemName(String name) {
        this.name = name;
    }

    public static ItemName of(String name) {
        return new ItemName(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemName itemName = (ItemName) o;
        return name.equals(itemName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
