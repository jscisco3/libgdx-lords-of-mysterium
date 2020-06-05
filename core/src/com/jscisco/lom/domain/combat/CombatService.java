package com.jscisco.lom.domain.combat;

import com.jscisco.lom.domain.entity.GameObject;

public class CombatService {

    public void handleAttack(GameObject attacker, GameObject defender) {
        defender.damage(new Damage(10, Damage.Type.PHYSICAL));
    }

}
