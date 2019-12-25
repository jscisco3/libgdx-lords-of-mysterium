package com.jscisco.lom.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.PlayerFactory;
import com.jscisco.lom.kingdom.Kingdom;
import com.jscisco.lom.util.Size3D;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KingdomManagementScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(ZoneScreen.class);

    private Kingdom kingdom;
    private LOMGame game;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private BitmapFont font;

    public KingdomManagementScreen(LOMGame game, Kingdom kingdom) {
        this.game = game;
        this.kingdom = kingdom;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        font = new BitmapFont();
        font.setColor(255, 255, 255, 1);

        batch = new SpriteBatch();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "[B]uild", camera.position.x - 300, camera.position.y + 250);
        font.draw(batch, "[R]esearch", camera.position.x - 300, camera.position.y + 225);
        font.draw(batch, "[H]ire Heroes", camera.position.x - 300, camera.position.y + 200);
        font.draw(batch, "[E]xplore Zone", camera.position.x - 300, camera.position.y + 175);
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
        if (input.isKeyJustPressed(Input.Keys.B)) {
            logger.warn("Building is not yet implemented.");
        }
        if (input.isKeyJustPressed(Input.Keys.R)) {
            logger.warn("Researching is not yet implemented.");
        }
        if (input.isKeyJustPressed(Input.Keys.H)) {
            logger.warn("Hiring heroes is not yet implemented.");
        }
        if (input.isKeyJustPressed(Input.Keys.E)) {
            this.game.setScreen(new ZoneScreen(this.game, new Zone(new Size3D(100, 80, 1), PlayerFactory.createRandomHero())));
        }
    }
}
