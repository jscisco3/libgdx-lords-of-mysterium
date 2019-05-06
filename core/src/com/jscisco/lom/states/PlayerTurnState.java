package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.MoveCommand;
import com.jscisco.lom.dungeon.Dungeon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTurnState extends State {

    private Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);

    private Actor player;

    public PlayerTurnState(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public void update() {
    }

    @Override
    public void handleInput(Input input) {
        if (input.isKeyPressed(Input.Keys.UP)) {
            MoveCommand command = new MoveCommand(0, 1, 0);
            command.invoke(player);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            MoveCommand command = new MoveCommand(0, -1, 0);
            command.invoke(player);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            MoveCommand command = new MoveCommand(1, 0, 0);
            command.invoke(player);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            MoveCommand command = new MoveCommand(-1, 0, 0);
            command.invoke(player);
            endTurn();
        }
    }

    private void endTurn() {
        dungeon.popState();
        logger.info("Ending the player's turn...");
    }
}
