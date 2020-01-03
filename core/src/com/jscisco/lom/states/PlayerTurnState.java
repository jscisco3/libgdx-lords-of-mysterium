package com.jscisco.lom.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.jscisco.lom.action.*;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.log.Message;
import com.jscisco.lom.log.MessageElement;
import com.jscisco.lom.log.MessageLog;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PlayerTurnState extends State {

    private static final Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);
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

        if (input.isKeyJustPressed(Input.Keys.SPACE)) {
            MessageLog.get().add(new Message().withElement(new MessageElement("Log message!", Color.GREEN)));
        }

        if (input.isKeyJustPressed(Input.Keys.PERIOD) && shift(input)) {
            if (new DescendStairs(player).invoke().succeeded()) {
                zone.incrementStage(player);
                player.getStage().getStairsUpPosition().ifPresent(pos ->
                        player.setPosition(pos));
            }
        }

        if (input.isKeyJustPressed(Input.Keys.COMMA) && shift(input)) {
            if (new AscendStairs(player).invoke().succeeded()) {
                zone.decrementStage(player);
                player.getStage().getStairsDownPosition().ifPresent(pos ->
                        player.setPosition(pos));
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

    public boolean shift(Input input) {
        return input.isKeyPressed(Input.Keys.SHIFT_RIGHT) || input.isKeyPressed(Input.Keys.SHIFT_LEFT);
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
