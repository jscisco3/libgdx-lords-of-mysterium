package com.jscisco.lom.application;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jscisco.lom.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class LoadingScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(LoadingScreen.class);
    private final Stage stage;
    private final AssetManager assetManager;
    private final Label loadingProgress = new Label("", GameConfiguration.getSkin(), "default");

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
            game.setScreen(new KingdomScreen(this.game));
        }

        float progress = assetManager.getProgress();
        String loadingString = MessageFormat.format("Loading: {0}/{1}", progress * 100, 100);
//        logger.info(loadingString);
        // Display bar here
        this.loadingProgress.setText(loadingString);
        this.stage.draw();
    }
}
