package com.jscisco.lom.domain;

public class Name {

    final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }


}
