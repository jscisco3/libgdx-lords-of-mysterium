package com.jscisco.lom.domain.combat;

import com.jscisco.lom.domain.Entity;
import com.jscisco.lom.domain.GameObject;

public class CombatService {

    public void handleAttack(Entity attacker, Entity defender) {
        defender.damage(new Damage(10, Damage.Type.PHYSICAL));
    }

}
