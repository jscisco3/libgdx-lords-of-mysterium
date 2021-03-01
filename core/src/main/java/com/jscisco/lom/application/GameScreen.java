package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jscisco.lom.Game;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.InstantEffect;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class GameScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);

    private OrthographicCamera camera;

    Stage stage;
    Player player = ActorFactory.player();

    Level level;

    // UI Elements
    private AdventurerUI adventurerUI;
    private Vector3 playerUIOffset = new Vector3(200f, 0f, 0f);


    // Input
    private AdventureInputProcessor processor;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private float keyPressedTime = 0f;
    private float initialInputDelay = 0.25f;

    private int cameraWidth = GameConfiguration.SCREEN_WIDTH;
    private int cameraHeight = GameConfiguration.SCREEN_HEIGHT;

    Matrix4 levelBatchTransform = new Matrix4(playerUIOffset, new Quaternion(), new Vector3(1f, 1f, 1f));

    public GameScreen(Game game) {
        super(game);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        camera.update();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        processor = new AdventureInputProcessor(player);
        // Create a zone and a stage
        level = new Level();
        level.addEntityAtPosition(player, Position.of(1, 1));
        inputMultiplexer.addProcessor(processor);
        inputMultiplexer.addProcessor(stage);

        adventurerUI = new AdventurerUI(player, 0, 0, playerUIOffset.x, cameraHeight, Color.GRAY);
        adventurerUI.setWidth(playerUIOffset.x);
        adventurerUI.setHeight(Gdx.graphics.getHeight());
        adventurerUI.top();
        stage.addActor(adventurerUI);
        stage.setDebugAll(false);

        player.applyEffect(
                new InstantEffect()
                        .addModifier(new AttributeModifier()
                                .forAttribute(player.getAttributes().getMaxHealth())
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(player.getAttributes().getHealth())
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
        );
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput(delta);
        level.process();
        updateCamera();
        batch.setTransformMatrix(levelBatchTransform);
        level.draw(batch, camera);
        stage.draw();
    }

    private void updateCamera() {
        float x = player.getPosition().getX() * 24;
        float y = player.getPosition().getY() * 24;

        x = Math.min(Math.max(cameraWidth / 2f, x), level.getWidth() * 24f - (cameraWidth / 2f) + playerUIOffset.x);
        y = Math.min(Math.max(cameraHeight / 2f, y), level.getHeight() * 24f - cameraHeight / 2f + playerUIOffset.y);

        // Clamp x and y
        camera.position.set(x, y, 0);
        camera.update();
    }

    public void handleInput(float delta) {
        Set<Integer> keysDown = processor.getKeysDown();
        if (processor.isKeyDown() && keyPressedTime == 0f) {
            setPlayerAction(keysDown);
        }
        if (processor.isKeyDown()) {
            keyPressedTime += delta;
        }
        if (processor.isKeyDown() && keyPressedTime > initialInputDelay) {
            setPlayerAction(keysDown);
        }
        if (!processor.isKeyDown()) {
            keyPressedTime = 0f;
        }
    }

    private void setPlayerAction(Set<Integer> input) {
        if (input.contains(Input.Keys.UP)) {
            player.setAction(new WalkAction(player, Direction.N));
        }
        if (input.contains(Input.Keys.DOWN)) {
            player.setAction(new WalkAction(player, Direction.S));
        }
        if (input.contains(Input.Keys.LEFT)) {
            player.setAction(new WalkAction(player, Direction.W));
        }
        if (input.contains(Input.Keys.RIGHT)) {
            player.setAction(new WalkAction(player, Direction.E));
        }
        if (input.contains(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }
}
