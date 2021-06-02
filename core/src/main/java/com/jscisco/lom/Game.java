package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.application.LoadingScreen;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Game extends ApplicationAdapter {
    Screen screen;
    private Assets assets = new Assets();
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
        setScreen(new LoadingScreen(this));

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
