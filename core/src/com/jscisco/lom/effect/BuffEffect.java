package com.jscisco.lom.effect;

import com.jscisco.lom.entity.AbstractStat;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.StatBonus;

// Note: This is permanent when applied unless explicitly destroyed or removed from the stat that
// received the bonus.
public class BuffEffect extends Effect {

    private AbstractStat stat;
    private StatBonus bonus;

    public BuffEffect(AbstractStat stat, StatBonus bonus) {
        this.stat = stat;
        this.bonus = bonus;
    }

    @Override
    public void apply(Entity entity) {
        stat.applyBonus(bonus);
    }

    public void destroy() {
        stat.removeBonus(bonus);
    }

}
