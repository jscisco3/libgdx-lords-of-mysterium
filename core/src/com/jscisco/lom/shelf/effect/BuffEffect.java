package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.entity.AbstractStat;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.entity.StatBonus;

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
