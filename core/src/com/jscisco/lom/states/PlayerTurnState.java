package com.jscisco.lom.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.jscisco.lom.action.*;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        if (input.isKeyJustPressed(Input.Keys.UP)) {
            action = new MoveAction(player, 0, 1);
        }
        if (input.isKeyJustPressed(Input.Keys.DOWN)) {
            action = new MoveAction(player, 0, -1);
        }
        if (input.isKeyJustPressed(Input.Keys.RIGHT)) {
            action = new MoveAction(player, 1, 0);
        }
        if (input.isKeyJustPressed(Input.Keys.LEFT)) {
            action = new MoveAction(player, -1, 0);
        }
        if (input.isKeyJustPressed(Input.Keys.Z)) {
            zone.pushState(new AutoexploreState(zone));
        }
        if (input.isKeyJustPressed(Input.Keys.R)) {
            action = new RestAction(player);
        }

        if (input.isKeyJustPressed(Input.Keys.RIGHT_BRACKET)) {
            if (new DescendStairs(player).invoke().succeeded()) {
                zone.incrementStage();
            }
        }

        if (input.isKeyJustPressed(Input.Keys.LEFT_BRACKET)) {
            if (new AscendStairs(player).invoke().succeeded()) {
                zone.decrementStage();
            }
        }

        if (input.isKeyJustPressed(Input.Keys.COMMA)) {
            List<Item> items = zone.getCurrentStage().getItemsAtPosition(player.getPosition());
            if (!items.isEmpty()) {
                action = new PickupItemAction(player, items.get(0));
            }
        }
        if (input.isKeyJustPressed(Input.Keys.D)) {
            if (!player.getInventory().getItems().isEmpty()) {
                action = new DropItemAction(player, player.getInventory().getItems().get(0));
            }
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
