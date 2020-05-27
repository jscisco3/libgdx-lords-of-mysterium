package com.jscisco.lom.domain.ability;

import org.apache.commons.lang3.StringUtils;

public class AbilityName {
    String name;

    public AbilityName(String name) {
        assert !StringUtils.isEmpty(name);
        this.name = name;
    }
}
