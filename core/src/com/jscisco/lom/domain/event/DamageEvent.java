package com.jscisco.lom.domain.event;

import com.jscisco.lom.domain.Entity;
import com.jscisco.lom.domain.combat.Damage;
import com.jscisco.lom.domain.GameObject;

public class DamageEvent extends Event {

    final Damage damage;
    final Entity target;

    public DamageEvent(Damage damage, Entity target) {
        this.damage = damage;
        this.target = target;
    }

    public Damage getDamage() {
        return damage;
    }

    public Entity getTarget() {
        return target;
    }
}
