package com.jscisco.lom.effect;

import com.jscisco.lom.combat.Damage;

public class TimedDamageEffect extends TimedEffect {

    private Damage damage;

    public TimedDamageEffect(int timer, Damage damage) {
        super(timer);
        this.damage = damage;
    }

    @Override
    public void tick() {
        super.tick();
        this.entity.ifPresent(e -> e.getHealth().damage(this.damage.getDamage()));
    }
}
