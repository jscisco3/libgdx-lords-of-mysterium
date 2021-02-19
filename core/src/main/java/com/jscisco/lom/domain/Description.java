package com.jscisco.lom.domain;

public class Description {
    final String description;

    private Description(String description) {
        this.description = description;
    }

    public static Description of(String description) {
        return new Description(description);
    }
}
