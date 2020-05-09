package com.jscisco.lom.domain.item;

import com.jscisco.lom.domain.combat.Accuracy;
import com.jscisco.lom.domain.combat.DamageRange;

public class Weapon {
    Accuracy accuracy;
    DamageRange damageRange;

    public Weapon(Accuracy accuracy, DamageRange damageRange) {
        this.accuracy = accuracy;
        this.damageRange = damageRange;
    }
}
