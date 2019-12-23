package com.jscisco.lom.effect;

import com.jscisco.lom.entity.Entity;

import java.util.Optional;

public abstract class TimedEffect {

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

    public void attach(Entity entity) {
        this.entity = Optional.of(entity);
    }

    public int timeRemaining() {
        return this.timer;
    }
}
