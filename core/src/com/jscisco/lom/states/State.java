package com.jscisco.lom.states;

import com.artemis.World;
import com.badlogic.gdx.Input;

public abstract class State {

    private World world;

    public State(World world) {
        this.world = world;
    }

    public abstract void update();

    public abstract void handleInput(Input input);

}
