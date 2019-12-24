package com.jscisco.lom.effect;

public class TimedDamageEffect extends TimedEffect {

    private DamageEffect damage;

    public TimedDamageEffect(int timer, DamageEffect damage) {
        super(timer);
        this.damage = damage;
    }

    @Override
    public void tick() {
        super.tick();
        this.entity.ifPresent(e -> this.damage.apply(e));
    }
}
