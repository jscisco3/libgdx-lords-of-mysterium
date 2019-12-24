package com.jscisco.lom.effect;

import com.jscisco.lom.entity.AbstractStat;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.StatBonus;

public class TimedBuffEffect extends TimedEffect {

    private AbstractStat stat;
    private StatBonus bonus;

    public TimedBuffEffect(int timer, AbstractStat stat, StatBonus bonus) {
        super(timer);
        this.stat = stat;
        this.bonus = bonus;
    }

    @Override
    public void destroy() {
        stat.removeBonus(bonus);
        super.destroy();
    }

    @Override
    public void apply(Entity entity) {
        super.apply(entity);
        stat.applyBonus(bonus);
    }
}
