package com.jscisco.lom.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(DungeonScreen.class);

    private Dungeon dungeon;
    private SpriteBatch batch;


    public DungeonScreen() {
        dungeon = new Dungeon(new Size3D(100, 80, 1));
        batch = new SpriteBatch();

//        stage.addActor(dungeon);

//        logger.info("Actors before adding tiles: " + stage.getActors().size);
//
//        for (int e : subscription.getEntities().getData()) {
//            stage.addActor(mTile.get(e).actor);
//        }
//
//        logger.info("Actors after adding tiles: " + stage.getActors().size);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                if (dungeon.getFloor()[x][y] == '.') {
                    batch.draw(Assets.floor, x * 24.0f, y * 24.0f);
                }
                if (dungeon.getFloor()[x][y] == '#') {
                    batch.draw(Assets.wall, x * 24.0f, y * 24.0f);
                }
            }
        }

        batch.draw(Assets.player, dungeon.getPlayer().getPosition().getX() * 24.0f, dungeon.getPlayer().getPosition().getY() * 24.0f);

        batch.end();

        dungeon.getCurrentState().handleInput(Gdx.input);
        dungeon.getCurrentState().update();

        logger.info("Frames per second: " + Gdx.graphics.getFramesPerSecond());

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
