package com.jscisco.lom.domain.entity;

/**
 * A Skill represents an entities ability to do certain actions. For example, the Smithing skill allows
 * a character to build more or higher quality items.
 */
public class SkillDefinition {
    private final String name;
    private final String description;

    public SkillDefinition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

