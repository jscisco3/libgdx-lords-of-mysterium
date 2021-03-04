package com.jscisco.lom.application;

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
    private final Assets assets;
    private final Label loadingProgress = new Label("", GameConfiguration.getSkin(), "default");
    private final long startTimeLoading = TimeUtils.millis();

    public LoadingScreen(Game game) {
        super(game);
        assets = game.getAssets();
        this.stage = new Stage();
        this.loadingProgress.setPosition(this.stage.getWidth() / 2f, this.stage.getHeight() / 2f);
        this.stage.addActor(loadingProgress);
        this.assets.load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (assets.getAssetManager().update()) {
            logger.info(MessageFormat.format("Loaded assets in {0}ms", TimeUtils.timeSinceMillis(startTimeLoading)));
            game.setScreen(new TitleScreen(this.game));
        }

        float progress = assets.getAssetManager().getProgress();
        String loadingString = MessageFormat.format("Loading: {0}/{1}", progress * 100, 100);
//        logger.info(loadingString);
        // Display bar here
        this.loadingProgress.setText(loadingString);
        this.stage.draw();
    }
}
