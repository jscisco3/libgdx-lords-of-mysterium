package com.jscisco.lom.domain.ability;

public abstract class Ability {
    final AbilityName name;
    final AbilityDescription description;

    public Ability(AbilityName name, AbilityDescription description) {
        this.name = name;
        this.description = description;
    }

    public abstract void invoke();

}
