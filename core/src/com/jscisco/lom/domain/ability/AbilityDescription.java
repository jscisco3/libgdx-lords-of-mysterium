package com.jscisco.lom.domain.ability;

import org.apache.commons.lang3.StringUtils;

public class AbilityDescription {
    final String description;

    private AbilityDescription(String description) {
        if (StringUtils.isEmpty(description)) {
            throw new IllegalArgumentException("Description is required");
        }
        this.description = description;
    }

    public static AbilityDescription of(String description) {
        return new AbilityDescription(description);
    }

}
