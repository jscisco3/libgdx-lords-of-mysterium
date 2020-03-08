package com.jscisco.lom.entity;

public class EntityName {

    private String name;

    public EntityName(String name) {
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
