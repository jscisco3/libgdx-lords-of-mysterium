package com.jscisco.lom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.screens.DungeonScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);
    private DungeonScreen dungeonScreen;

    public static RNG rng = new RNG(0xDEADBEEF);

    // WINDOW Sizes
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    @Override
    public void create() {
        Assets.load();
        dungeonScreen = new DungeonScreen();
        setScreen(dungeonScreen);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
