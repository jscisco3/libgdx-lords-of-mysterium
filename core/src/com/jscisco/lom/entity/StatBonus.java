package com.jscisco.lom.entity;

import java.util.Objects;

public class StatBonus {
    private int value = 0;

    public StatBonus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatBonus statBonus = (StatBonus) o;
        return value == statBonus.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
