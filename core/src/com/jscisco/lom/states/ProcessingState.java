package com.jscisco.lom.states;

import com.artemis.World;
import com.badlogic.gdx.Input;

public class ProcessingState extends State {

    private World world;

    public ProcessingState(World world) {
        super(world);
        world.inject(this);
    }

    @Override
    public void update() {
        world.process();
    }

    @Override
    public void handleInput(Input input) {
        // Processing state is just responsible for making sure the world is processing
    }
}
