package com.jscisco.lom.domain.ability;

import org.apache.commons.lang3.StringUtils;

public class AbilityName {
    final String name;

    private AbilityName(String name) {
        assert !StringUtils.isEmpty(name);
        this.name = name;
    }

    public static AbilityName of(String name) {
        return new AbilityName(name);
    }

}
