package com.jscisco.lom.effect;

import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.entity.Entity;

public class DamageEffect {

    private Damage damage;

    public DamageEffect(Damage damage) {
        this.damage = damage;
    }

    public void apply(Entity entity) {
        // Calculate resistances
        // Apply the damagess
        entity.getHealth().damage(this.damage.getDamage());
    }

}
