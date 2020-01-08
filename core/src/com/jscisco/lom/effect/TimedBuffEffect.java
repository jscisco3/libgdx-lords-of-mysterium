package com.jscisco.lom.effect;

import com.jscisco.lom.entity.Entity;

public class TimedBuffEffect extends TimedEffect {

    private BuffEffect effect;

    public TimedBuffEffect(int timer, BuffEffect effect) {
        super(timer);
        this.effect = effect;
    }

    @Override
    public void destroy() {
        super.destroy();
        this.effect.destroy();
    }

    @Override
    public void apply(Entity entity) {
        super.apply(entity);
        this.effect.apply(entity);
    }
}
