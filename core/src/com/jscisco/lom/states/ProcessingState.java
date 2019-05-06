package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.dungeon.Dungeon;

public class ProcessingState extends State {

    public ProcessingState(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public void update() {
    }

    @Override
    public Command handleInput(Input input) {
        // Processing state is just responsible for making sure the world is processing
        return null;
    }
}
