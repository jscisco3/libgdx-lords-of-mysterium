package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jscisco.lom.Game;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class GameScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);
    Stage stage = new Stage();
    Player player = ActorFactory.player();
    AdventureInputProcessor processor;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    Level level;

    public GameScreen(Game game) {
        super(game);
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        processor = new AdventureInputProcessor(player);
        // Create a zone and a stage
        level = new Level();
        level.addEntityAtPosition(player, Position.of(1, 1));
        inputMultiplexer.addProcessor(processor);
        inputMultiplexer.addProcessor(stage);
        logger.info("In constructor..." + Gdx.input.getInputProcessor().toString());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
        logger.info("In show()..." + Gdx.input.getInputProcessor().toString());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        level.draw(batch);
        batch.begin();
        stage.draw();
        batch.end();
        level.process();
    }

    public void handleInput() {
        Set<Integer> keysDown = processor.getKeysDown();
        if (keysDown.contains(Input.Keys.UP)) {
            player.setAction(new WalkAction(player, Direction.N));
        }
        if (keysDown.contains(Input.Keys.DOWN)) {
            player.setAction(new WalkAction(player, Direction.S));
        }
        if (keysDown.contains(Input.Keys.LEFT)) {
            player.setAction(new WalkAction(player, Direction.W));
        }
        if (keysDown.contains(Input.Keys.RIGHT)) {
            player.setAction(new WalkAction(player, Direction.E));
        }
    }
}
