package com.jscisco.lom.application;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;
import com.jscisco.lom.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class LoadingScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
    private final Stage stage;
    private final AssetManager assetManager;
    private final Label loadingProgress = new Label("", GameConfiguration.getSkin(), "default");
    private long startTimeLoading = TimeUtils.millis();

    public LoadingScreen(Game game) {
        super(game);
        assetManager = game.getAssetManager();
        this.stage = new Stage();
        this.loadingProgress.setPosition(this.stage.getWidth() / 2f, this.stage.getHeight() / 2f);
        this.stage.addActor(loadingProgress);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (assetManager.update()) {
            logger.info(MessageFormat.format("Loaded assets in {0}ms", TimeUtils.timeSinceMillis(startTimeLoading)));
            game.setScreen(new KingdomScreen(this.game));
        }

        float progress = assetManager.getProgress();
        String loadingString = MessageFormat.format("Loading: {0}/{1}", progress * 100, 100);
//        logger.info(loadingString);
        // Display bar here
        this.loadingProgress.setText(loadingString);
        this.stage.draw();
    }

    private void loadTextures() {
        // Terrain features
        loadTexture("textures/features/floor.png");
        loadTexture("textures/features/wall.png");
        // Characters
        loadTexture("textures/entities/warrior.png");
        loadTexture("textures/entities/golem.png");
        // Kingdom
        loadTexture("textures/kingdom/parchmentAncient.png");

    }

    private void loadTexture(String filename) {
        assetManager.load(filename, Texture.class);
    }
}
