package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jscisco.lom.Game;

public abstract class AbstractScreen extends ScreenAdapter {

    protected Game game;
    protected Stage stage;
    protected SpriteBatch batch = new SpriteBatch();

    public AbstractScreen(Game game) {
        this.game = game;
        this.stage = new Stage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
    }
}
