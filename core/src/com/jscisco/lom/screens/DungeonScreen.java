package com.jscisco.lom.screens;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Position3D;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(DungeonScreen.class);

    private ComponentMapper<PositionComponent> mPosition;

    private Stage stage;
    private Dungeon dungeon;

    private float DEFAULT_TILE_WIDTH = 24.0f;
    private float DEFAULT_TILE_HEIGHT = 24.0f;

    public DungeonScreen() {
        stage = new Stage();
        dungeon = new Dungeon(new Size3D(100, 100, 5));
        dungeon.getWorld().inject(this);
        stage.addActor(dungeon);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();
        stage.act(delta);
        stage.draw();
        dungeon.getCurrentState().handleInput(Gdx.input);
        dungeon.getCurrentState().update();

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

    private void updateCamera() {
        Position3D position = mPosition.get(dungeon.getPlayer()).position;

        float halfWidth = stage.getWidth() / 2.0f;
        float halfHeight = stage.getHeight() / 2.0f;

        float newX = position.getX() * DEFAULT_TILE_WIDTH;
        float newY = position.getY() * DEFAULT_TILE_HEIGHT;

        if (newX > halfWidth && newX < stage.getWidth()) {
            stage.getCamera().position.x = position.getX() * DEFAULT_TILE_WIDTH;
        }
        if (newY > halfHeight && newY < stage.getHeight()) {
            stage.getCamera().position.y = position.getY() * DEFAULT_TILE_HEIGHT;
        }
        stage.getCamera().update();
    }
}
