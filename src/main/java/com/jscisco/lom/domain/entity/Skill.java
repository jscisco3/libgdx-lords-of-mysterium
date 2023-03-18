package com.jscisco.lom.domain.entity;

/**
 * A Skill represents an entities ability to do certain actions. For example, the Smithing skill allows
 * a character to build more or higher quality items.
 */
public class Skill {

    private final SkillDefinition skillDefinition;
    private int currentExperience;

    public Skill(SkillDefinition skillDefinition) {
        this.skillDefinition = skillDefinition;
        this.currentExperience = 0;
    }

    public Skill(SkillDefinition skillDefinition, int currentExperience) {
        this.skillDefinition = skillDefinition;
        this.currentExperience = currentExperience;
    }

    public int getLevel() {
        return currentExperience / 1000;
    }

    public void gainExperience(int experience) {
        this.currentExperience += experience;
    }

    public String getName() {
        return this.skillDefinition.getName();
    }

    public String getDescription() {
        return this.skillDefinition.getDescription();
    }
}

