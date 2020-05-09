package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.entity.Entity;

public class DamageEffect extends Effect {

    private Damage damage;

    public DamageEffect(Damage damage) {
        this.damage = damage;
    }

    public void apply(Entity entity) {
        // Calculate resistances
        // Apply the damagess
        entity.getHealth().reduce(this.damage.getDamage());
    }

}
