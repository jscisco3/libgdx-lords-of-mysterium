package com.jscisco.lom.application;

import com.jscisco.lom.domain.entity.SkillDefinition;

// TODO: Parse this from a CSV or json file
public class Skills {
    public static SkillDefinition oneHanded = new SkillDefinition(
            "One Handed",
            "The ability to use one handed weapons"
    );
    public static SkillDefinition twoHanded = new SkillDefinition(
            "Two Handed",
            "The ability to use two handed weapons"
    );
    public static SkillDefinition conjuration = new SkillDefinition(
            "Conjuration",
            "The ability to use conjuration magic"
    );
    public static SkillDefinition smithing = new SkillDefinition(
            "Smithing",
            "The ability to smith weapons and armor"
    );
}
