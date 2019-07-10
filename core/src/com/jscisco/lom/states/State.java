package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.zone.Zone;

public abstract class State {

    protected Zone zone;

    public State(Zone zone) {
        this.zone = zone;
    }

    public abstract void update();

    public abstract void handleInput(Input input);

    public abstract void start();

    public abstract void stop();

}
