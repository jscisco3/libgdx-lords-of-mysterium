package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.assets.Assets;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;

    @Override
    public void create() {
        Assets.load();
        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Assets.floor, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();
    }
}
