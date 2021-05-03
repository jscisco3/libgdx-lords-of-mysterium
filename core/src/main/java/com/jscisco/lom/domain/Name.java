package com.jscisco.lom.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

    private final String name;

    private Name() {
        this.name = "NO_NAME";
    }

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
