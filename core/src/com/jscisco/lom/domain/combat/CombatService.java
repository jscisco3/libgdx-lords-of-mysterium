package com.jscisco.lom.domain.combat;

import com.jscisco.lom.domain.entity.AbstractEntity;

public class CombatService {

    public void handleAttack(AbstractEntity attacker, AbstractEntity defender) {
        defender.damage(new Damage(10, Damage.Type.PHYSICAL));
    }

}
