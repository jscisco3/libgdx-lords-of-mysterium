package com.jscisco.lom.domain.item;

import org.apache.commons.lang3.StringUtils;

public class ItemName {
    String name;

    public ItemName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }
}
