package com.jscisco.lom.shelf.domain;

import java.util.Objects;

public final class EntityName {
    private String name;

    public EntityName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityName that = (EntityName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "EntityName{" +
                "name='" + name + '\'' +
                '}';
    }
}
