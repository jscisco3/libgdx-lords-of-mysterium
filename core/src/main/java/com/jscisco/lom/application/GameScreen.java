package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    private OrthographicCamera camera;

    Stage stage = new Stage();
    Player player = ActorFactory.player();
    AdventureInputProcessor processor;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    Level level;

    // UI Elements
    Label cameraPosition;

    float cameraWidth = 1280;
    float cameraHeight = 900;

    public GameScreen(Game game) {
        super(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        camera.update();
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        cameraPosition = new Label("Camera Position", skin);
        processor = new AdventureInputProcessor(player);
        // Create a zone and a stage
        level = new Level();
        level.addEntityAtPosition(player, Position.of(1, 1));
        inputMultiplexer.addProcessor(processor);
        inputMultiplexer.addProcessor(stage);
        stage.addActor(cameraPosition);
        cameraPosition.setPosition(0, stage.getHeight() - 50f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        level.process();
        updateCamera();
        level.draw(batch, camera);
        stage.draw();
//        batch.begin();
//        batch.end();
    }

    private void updateCamera() {
        float x = player.getPosition().getX() * 24;
        float y = player.getPosition().getY() * 24;

        x = Math.min(Math.max(cameraWidth / 2f - 200f, x), level.getWidth() * 24f - (cameraWidth / 2f));
        y = Math.min(Math.max(cameraHeight / 2f, y), level.getHeight() * 24f - cameraHeight / 2f);

        // Clamp x and y
        camera.position.set(x, y, 0);
        cameraPosition.setText(String.format("Camera position: (%f, %f)", x, y));
        camera.update();
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
        if (keysDown.contains(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
