package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.commands.MoveCommand;
import com.jscisco.lom.dungeon.Dungeon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTurnState extends State {

    private Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);
    private Actor player;

    public PlayerTurnState(Dungeon dungeon) {
        super(dungeon);
        this.player = dungeon.getPlayer();
    }

    @Override
    public void update() {
    }

    @Override
    public Command handleInput(Input input) {
        Command command = null;
        if (input.isKeyPressed(Input.Keys.UP)) {
            command = new MoveCommand(dungeon, player, 0, 1, 0);
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            command = new MoveCommand(dungeon, player, 0, -1, 0);
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            command = new MoveCommand(dungeon, player, 1, 0, 0);
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            command = new MoveCommand(dungeon, player, -1, 0, 0);
        }
        if (input.isKeyJustPressed(Input.Keys.Z)) {
            dungeon.pushState(new AutoexploreState(dungeon));
        }
        return command;
    }

    private void endTurn() {
        dungeon.popState();
    }
}
