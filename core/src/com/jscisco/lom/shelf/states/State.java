package com.jscisco.lom.shelf.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.shelf.LOMGame_Deprecated;

public abstract class State {

    protected transient LOMGame_Deprecated game;

    public State(LOMGame_Deprecated game) {
        this.game = game;
    }

    public abstract void update();

    public abstract void handleInput(Input input);

    public abstract void start();

    public abstract void stop();

}
