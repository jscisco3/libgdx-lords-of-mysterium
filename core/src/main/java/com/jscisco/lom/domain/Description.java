package com.jscisco.lom.domain;

public class Description {
    private String description;

    private Description() {}

    private Description(String description) {
        this.description = description;
    }

    public static Description of(String description) {
        return new Description(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
