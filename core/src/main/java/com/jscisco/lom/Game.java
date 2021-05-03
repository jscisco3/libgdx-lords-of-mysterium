package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.application.LoadingScreen;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.configuration.ApplicationConfiguration;
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        GameRepository repo = ctx.getBean(GameRepository.class);
        repo.save(new com.jscisco.lom.domain.Game());

        com.jscisco.lom.domain.Game g = repo.getById(1L);
        logger.info(String.valueOf(g.getId()));
        // Initialize everything
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
