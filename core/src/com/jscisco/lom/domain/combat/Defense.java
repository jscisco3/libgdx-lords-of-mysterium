package com.jscisco.lom.domain.combat;

import javax.annotation.Nonnull;

public class Defense {
    Armor armor;
    Evasion evasion;

    private Defense(@Nonnull Armor armor, @Nonnull Evasion evasion) {
        this.armor = armor;
        this.evasion = evasion;
    }

    public static Defense of(Armor armor, Evasion evasion) {
        return new Defense(armor, evasion);
    }

}
