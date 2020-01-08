package com.jscisco.lom.ability;

public class AbilityName {
    private String name;

    public AbilityName(String name) {
        this.name = name;
    }

    public static AbilityName of(String name) {
        return new AbilityName(name);
    }

    public String getName() {
        return this.name;
    }

}
