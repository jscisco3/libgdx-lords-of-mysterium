package com.jscisco.lom.domain.combat;

import com.jscisco.lom.domain.Entity;

public final class Combat {

    private Combat() {
    }

    public void handleAttack(Attack attack, Entity attacker, Entity defender) {
        Defense defense = defender.getBaseDefense();
        if (defense.evasion.evasion > attack.accuracy.accuracy) {
            // Miss
            return;
        }
        Damage damage = attack.getDamage();
        damage.reduce(defense.armor.armor);
        defender.takeDamage(attack.getDamage());
    }

}
