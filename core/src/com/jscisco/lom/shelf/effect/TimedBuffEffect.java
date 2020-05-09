package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.entity.Entity;

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
