package com.jscisco.lom.domain.event;

import com.jscisco.lom.domain.combat.Damage;
import com.jscisco.lom.domain.entity.GameObject;

public class DamageEvent extends Event {

    final Damage damage;
    final GameObject target;

    public DamageEvent(Damage damage, GameObject target) {
        this.damage = damage;
        this.target = target;
    }

    public Damage getDamage() {
        return damage;
    }

    public GameObject getTarget() {
        return target;
    }
}
