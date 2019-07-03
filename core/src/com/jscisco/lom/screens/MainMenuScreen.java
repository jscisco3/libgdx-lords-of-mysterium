package com.jscisco.lom.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.kingdom.Kingdom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(MainMenuScreen.class);

    private Game game;
    private OrthographicCamera camera;
    private BitmapFont font;
    private SpriteBatch batch;

    public MainMenuScreen(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, LOMGame.WIDTH, LOMGame.HEIGHT);
        font = new BitmapFont();
        font.setColor(255, 255, 255, 1);

        batch = new SpriteBatch();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "[N]ew Game?", camera.position.x - 300, camera.position.y + 250);
        font.draw(batch, "[L]oad Game?", camera.position.x - 300, camera.position.y + 225);
        font.draw(batch, "[Q]uit Game?", camera.position.x - 300, camera.position.y + 200);
        batch.end();

        handleInput();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void handleInput() {
        Input input = Gdx.input;
        if (input.isKeyJustPressed(Input.Keys.N)) {
            this.game.setScreen(new KingdomManagementScreen(this.game, new Kingdom()));
        }
        if (input.isKeyJustPressed(Input.Keys.L)) {
            logger.warn("Loading games is not yet implemented!");
        }
        if (input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }
}
