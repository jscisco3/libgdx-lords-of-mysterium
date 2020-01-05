package com.jscisco.lom.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.action.*;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.log.Message;
import com.jscisco.lom.log.MessageElement;
import com.jscisco.lom.log.MessageLog;
import com.jscisco.lom.screens.ZoneScreen;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class PlayerTurnState extends State {

    private static final Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);
    private Player player;
    private Stage stage;
    private Zone zone;

    public PlayerTurnState(LOMGame game, Player player, Zone zone) {
        super(game);
        this.game = game;
        this.player = player;
        this.zone = zone;
        this.stage = player.getStage();
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
            game.pushState(new AutoexploreState(this.game, this.stage));
        }
        if (input.isKeyJustPressed(Input.Keys.R)) {
            action = new RestAction(player);
        }
        if (input.isKeyJustPressed(Input.Keys.T)) {
            // Get confirmation
            // If confirmed, teleport!
            Optional<Position> pos = stage.findEmptyPosition();
            while (!pos.isPresent()) {
                pos = stage.findEmptyPosition();
            }
            Position p = pos.get();
            action = new MoveAction(player, p.getX() - player.getX(), p.getY() - player.getY());
            game.requireConfirmation();
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
            List<Item> items = stage.getItemsAtPosition(player.getPosition());
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
}
