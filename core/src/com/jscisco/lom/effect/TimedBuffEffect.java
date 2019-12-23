package com.jscisco.lom.effect;

import com.jscisco.lom.entity.AbstractStat;
import com.jscisco.lom.entity.Entity;

public class TimedBuffEffect extends TimedEffect {

    public TimedBuffEffect(int timer, AbstractStat stat) {
        super(timer);
    }

    @Override
    public void destroy() {
        this.entity.ifPresent(e -> e.getStatistics());
        super.destroy();
    }

    @Override
    public void attach(Entity entity) {
        super.attach(entity);
    }
}
