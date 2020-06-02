package com.jscisco.lom.domain.ability;

import org.apache.commons.lang3.StringUtils;

public class AbilityDescription {
    final String description;

    public AbilityDescription(String description) {
        if (StringUtils.isEmpty(description)) {
            throw new IllegalArgumentException("Description is required");
        }
        this.description = description;
    }
}
