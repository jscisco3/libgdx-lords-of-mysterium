package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.actor.Actor;

public class Door extends Feature {

    private boolean open = false;

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }

    public boolean isOpen() {
        return this.open;
    }

    @Override
    public boolean isWalkable(Actor actor) {
        return open;
    }
}
