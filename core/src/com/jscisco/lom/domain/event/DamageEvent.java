package com.jscisco.lom.domain.event;

import com.jscisco.lom.domain.combat.Damage;
import com.jscisco.lom.domain.entity.AbstractEntity;

public class DamageEvent extends Event {

    final Damage damage;
    final AbstractEntity target;

    public DamageEvent(Damage damage, AbstractEntity target) {
        this.damage = damage;
        this.target = target;
    }

    public Damage getDamage() {
        return damage;
    }

    public AbstractEntity getTarget() {
        return target;
    }
}
