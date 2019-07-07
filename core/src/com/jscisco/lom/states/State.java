package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.dungeon.Dungeon;

public abstract class State {

    protected Dungeon dungeon;

    public State(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public abstract void update();

    public abstract void handleInput(Input input);

    public abstract void start();

    public abstract void stop();

}
