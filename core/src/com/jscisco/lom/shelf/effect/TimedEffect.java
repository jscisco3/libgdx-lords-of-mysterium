package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.entity.Entity;

import java.util.Optional;

public abstract class TimedEffect extends Effect {

    // The entity affected by this effect
    protected Optional<Entity> entity = Optional.empty();
    private int timer;

    public TimedEffect(int timer) {
        this.timer = timer;
    }

    public void tick() {
        this.timer -= 1;
        if (this.timer == 0) {
            this.destroy();
        }
    }

    public void destroy() {
        this.entity.ifPresent(e -> e.removeEffect(this));
    }

    public void apply(Entity entity) {
        this.entity = Optional.of(entity);
        entity.getEffects().add(this);
    }

    public int timeRemaining() {
        return this.timer;
    }
}
