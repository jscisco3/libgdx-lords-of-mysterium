package com.jscisco.lom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.jscisco.lom.application.EventProcessor;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

import java.util.Optional;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);

    public static final RNG rng = new RNG(0xDEADBEEF);

    EventProcessor processor;
    Player player = new Player(new EntityId(rng.nextLong()), new EntityName("Jack"));

    public LOMGame() {
        this.processor = new EventProcessor();
        // Register a bad event listener to test it.
        this.player.registerHandler(new EventHandler() {
            @Override
            public void handle(Event event) {
                logger.info("Handling event of type: {}", event.getClass());
            }
        });
        processor.attach(this.player);
    }

    @Override
    public void create() {
        Assets.load();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();

        Input input = Gdx.input;
        if (input.isKeyPressed(Input.Keys.UP)) {
            this.player.nextAction(new MoveAction(this.player, this.player.position().add(0, 1)));
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            this.player.nextAction(new MoveAction(this.player, this.player.position().add(0, -1)));
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            this.player.nextAction(new MoveAction(this.player, this.player.position().add(-1, 0)));
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            this.player.nextAction(new MoveAction(this.player, this.player.position().add(1, 0)));
        }
        Optional.ofNullable(player.nextAction()).ifPresent(a -> {
            ActionResult result = a.invoke();
            result.events().forEach(e -> processor.notifyObservers(e));
            logger.info("Action result: {} | Player position: {}", result.toString(), player.position());
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

}
