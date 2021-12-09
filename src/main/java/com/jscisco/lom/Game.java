package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.application.GameScreen;
import com.jscisco.lom.application.LoadingScreen;
import com.jscisco.lom.application.TitleScreen;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Game extends ApplicationAdapter {
    Screen screen;
    private Assets assets = null;
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    public void setScreen(Screen screen) {
        this.screen = screen;
        this.screen.show();
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void create() {
        // Initialize everything
        // Required for setting the context once! Has to be done.
        new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        GameConfiguration.configureEventBus();
        // TODO: Add packed/assets.atlas to resources
        assets = new Assets(
                new AssetManager(),
                new AssetDescriptor<TextureAtlas>(Gdx.files.internal("packed/assets.atlas").path(), TextureAtlas.class)
        );
        setScreen(new LoadingScreen(this));
//        setScreen(new TitleScreen(this));

    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
    }

    public Assets getAssets() {
        return assets;
    }
}
