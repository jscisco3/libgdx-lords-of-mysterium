package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.LOMGame;

public abstract class State {

    protected transient LOMGame game;

    public State(LOMGame game) {
        this.game = game;
    }

    public abstract void update();

    public abstract void handleInput(Input input);

    public abstract void start();

    public abstract void stop();

}
