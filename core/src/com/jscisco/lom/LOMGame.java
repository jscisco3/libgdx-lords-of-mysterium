package com.jscisco.lom;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.screens.DungeonScreen;
import com.jscisco.lom.systems.RenderSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);

    private DungeonScreen dungeonScreen;
    private World world;

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public void initializeWorld() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new RenderSystem())
                .build();
        world = new World(config);
        logger.info("initializing world....");
    }

    @Override
    public void create() {
        initializeWorld();
        Assets.load();
        dungeonScreen = new DungeonScreen(world);
        setScreen(dungeonScreen);
        logger.info("game created...");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.process();
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
