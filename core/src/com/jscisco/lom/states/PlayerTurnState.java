package com.jscisco.lom.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jscisco.lom.actor.Entity;
import com.jscisco.lom.commands.Action;
import com.jscisco.lom.commands.MoveAction;
import com.jscisco.lom.dungeon.Dungeon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTurnState extends State {

    private Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);
    private Entity player;

    public PlayerTurnState(Dungeon dungeon) {
        super(dungeon);
        this.player = dungeon.getPlayer();
    }

    @Override
    public void update() {
    }

    @Override
    public void handleInput(Input input) {
        Action action = null;
        if (input.isKeyPressed(Input.Keys.UP)) {
            action = new MoveAction(dungeon, player, 0, 1, 0);
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            action = new MoveAction(dungeon, player, 0, -1, 0);
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            action = new MoveAction(dungeon, player, 1, 0, 0);
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            action = new MoveAction(dungeon, player, -1, 0, 0);
        }
        if (input.isKeyJustPressed(Input.Keys.Z)) {
            dungeon.pushState(new AutoexploreState(dungeon));
        }
        if (input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        this.player.setNextAction(action);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    private void endTurn() {
        dungeon.popState();
    }
}
