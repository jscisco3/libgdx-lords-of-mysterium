package com.jscisco.lom.shelf.entity;


public class EntityName {

    private String name;

    public EntityName(String name) {
        if (name != null && name.isEmpty()) {
            throw new IllegalArgumentException("Entity Name cannot be empty");
        }
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "EntityName{" +
                "name='" + name + '\'' +
                '}';
    }
}
