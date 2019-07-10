package com.jscisco.lom.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTurnState extends State {

    private Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);
    private Entity player;

    public PlayerTurnState(Zone zone) {
        super(zone);
        this.player = zone.getCurrentStage().getPlayer();
    }

    @Override
    public void update() {
    }

    @Override
    public void handleInput(Input input) {
        Action action = null;
        if (input.isKeyPressed(Input.Keys.UP)) {
            action = new MoveAction(player, 0, 1);
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            action = new MoveAction(player, 0, -1);
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            action = new MoveAction(player, 1, 0);
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            action = new MoveAction(player, -1, 0);
        }
        if (input.isKeyJustPressed(Input.Keys.Z)) {
            zone.pushState(new AutoexploreState(zone));
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
        zone.popState();
    }
}
