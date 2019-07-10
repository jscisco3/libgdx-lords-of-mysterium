package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.dungeon.Zone;

public class ProcessingState extends State {

    public ProcessingState(Zone zone) {
        super(zone);
    }

    @Override
    public void update() {
    }

    @Override
    public void handleInput(Input input) {
        // Processing state is just responsible for making sure the world is processing
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
