package com.jscisco.lom.domain;


import org.apache.commons.lang3.StringUtils;

public class EntityName {
    String name;

    public EntityName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }
}
