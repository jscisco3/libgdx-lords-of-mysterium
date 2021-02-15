package com.jscisco.lom.domain.entity;

public class EntityName {

    final String name;

    private EntityName(String name) {
        this.name = name;
    }

    public static EntityName of(String name) {
        return new EntityName(name);
    }

}
