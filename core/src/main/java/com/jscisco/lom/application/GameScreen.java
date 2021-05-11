package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.application.services.GameService;
import com.jscisco.lom.application.ui.AdventurerUI;
import com.jscisco.lom.application.ui.GameLogUI;
import com.jscisco.lom.application.ui.InventoryWindow;
import com.jscisco.lom.application.ui.PickupItemWindow;
import com.jscisco.lom.application.ui.PopupWindow;
import com.jscisco.lom.domain.MathUtils;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class GameScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(GameScreen.class);
    private OrthographicCamera camera;

    SaveGame saveGame;

    Hero hero;
    Level level;

    // UI Elements
    private AdventurerUI adventurerUI;
    private GameLogUI gameLogUI;
    private Vector3 playerUIOffset = new Vector3(200f, 0f, 0f);
    private Vector3 gameLogUIOffset = new Vector3(playerUIOffset.x, 150f, 0f);

    private Stage popupStage = new Stage();

    // Input
    private AdventureInputProcessor processor;
    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private float keyPressedTime = 0f;
    private float initialInputDelay = 0.25f;

    private int cameraWidth = GameConfiguration.SCREEN_WIDTH;
    private int cameraHeight = GameConfiguration.SCREEN_HEIGHT;

    private final GameService gameService;

    Matrix4 levelBatchTransform = new Matrix4(playerUIOffset, new Quaternion(), new Vector3(1f, 1f, 1f));

    public GameScreen(Game game, SaveGame saveGame, Level level) {
        this(game, saveGame, level, level.getHero());
    }

    public GameScreen(Game game, SaveGame saveGame, Level level, Hero hero) {
        super(game);
        this.level = level;
        this.hero = hero;
        this.saveGame = saveGame;

        this.gameService = ServiceLocator.getBean(GameService.class);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, cameraWidth, cameraHeight);
        camera.update();
        // TODO: Is this fine?
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        processor = new AdventureInputProcessor();
        inputMultiplexer.addProcessor(processor);
        inputMultiplexer.addProcessor(stage);

        adventurerUI = new AdventurerUI(hero, 0, 0, playerUIOffset.x, cameraHeight, Color.GRAY);
        adventurerUI.setWidth(playerUIOffset.x);
        adventurerUI.setHeight(Gdx.graphics.getHeight());

        gameLogUI = new GameLogUI(GameConfiguration.gameLog, playerUIOffset.x, 0, GameConfiguration.SCREEN_WIDTH - playerUIOffset.x, gameLogUIOffset.y, new Color(0x7f7f7faa));
        gameLogUI.setPosition(playerUIOffset.x, 0f);
        gameLogUI.setWidth(GameConfiguration.SCREEN_WIDTH - playerUIOffset.x);
        gameLogUI.setHeight(gameLogUIOffset.y);

        adventurerUI.top();
        stage.addActor(adventurerUI);
        stage.addActor(gameLogUI);
        stage.setDebugAll(false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        handleInput(delta);
        level.process();
        updateCamera();
        batch.setTransformMatrix(levelBatchTransform);
        LevelRenderer.draw(batch, this.game.getAssets(), camera, level, hero);
        stage.act();
        stage.draw();
        popupStage.act();
        popupStage.draw();
    }

    private void updateCamera() {
        float x = hero.getPosition().getX() * 24;
        float y = hero.getPosition().getY() * 24;

        x = MathUtils.clamp(cameraWidth / 2f, level.getWidth() * 24f - (cameraWidth / 2f) + playerUIOffset.x, x);
        y = MathUtils.clamp(cameraHeight / 2f - gameLogUIOffset.y, level.getHeight() * 24f - cameraHeight / 2f, y);

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
        if (input.contains(Input.Keys.COMMA)) {
            PickupItemWindow window = new PickupItemWindow(hero, hero.getLevel().getTileAt(hero.getPosition()).getItems(), inputMultiplexer);
            popup(window);
            // Have to clear the input because otherwise, when we change the input processor... it still counts the character
            // as being pressed.
            input.clear();
        }
        // TODO: Consider opening Inventory window with prototype action
//        if (input.contains(Input.Keys.D)) {
//            hero.setAction(new DropItemAction(hero));
//        }
        // TODO: we just hide the window, and then we create a new window when we close it.
        // Consider how we can re-use or delete the old window.
        if (input.contains(Input.Keys.I)) {
            InventoryWindow inventory = new InventoryWindow("Inventory", hero, inputMultiplexer);
            popup(inventory);
            // Have to clear the input because otherwise, when we change the input processor... it still counts the character
            // as being pressed.
            input.clear();
        }
        if (input.contains(Input.Keys.ESCAPE)) {
            gameService.saveLevel(level);
            Gdx.app.exit();
        }
        hero.handleInput(input);
    }

    private void popup(PopupWindow popupWindow) {
        Gdx.input.setInputProcessor(popupStage);
        float newWidth = 400, newHeight = 200;
        popupWindow.setBounds((Gdx.graphics.getWidth() - newWidth) / 2,
                (Gdx.graphics.getHeight() - newHeight) / 2, newWidth, newHeight); //Center on screen.
        popupStage.addActor(popupWindow);
        popupStage.setScrollFocus(popupWindow.getScroller());
    }
}
