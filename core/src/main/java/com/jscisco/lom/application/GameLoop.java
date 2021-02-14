package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jscisco.lom.domain.action.Action;

import java.util.ArrayList;
import java.util.List;

public class GameLoop {

    private final List<Action> actions = new ArrayList<>();

    public void run() {
        boolean running = true;
        while (running) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                running = false;
            }
        }
    }
}
