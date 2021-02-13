package com.jscisco.lom.domain;

import com.badlogic.gdx.Screen;

public abstract class State {
    public abstract void render(Screen screen);
    public abstract void handleInput();
}
