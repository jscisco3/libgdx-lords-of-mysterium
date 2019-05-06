package com.jscisco.lom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.screens.DungeonScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);

    private DungeonScreen dungeonScreen;
//    private OrthographicCamera camera;

    // WINDOW Sizes
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    // Tiles in View
    public static int TILES_IN_VIEW_WIDTH = 10;
    public static int TILES_IN_VIEW_HEIGHT = 20;

    @Override
    public void create() {
        Assets.load();

//        camera = new OrthographicCamera(WIDTH/2, HEIGHT/2);
//        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
////        camera.position.set(1000.0f, 1000.0f, 0.0f);
//        camera.update();
        dungeonScreen = new DungeonScreen();
        setScreen(dungeonScreen);
        logger.info("game created...");
    }

    @Override
    public void render() {
//        camera.update();
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
