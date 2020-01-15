package com.jscisco.lom.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.ability.Ability;
import com.jscisco.lom.action.AbilityAction;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;

import java.util.ListIterator;

public class KnownAbilitiesScreen implements Screen {

    private LOMGame game;
    private Player player;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private BitmapFont font;

    public KnownAbilitiesScreen(LOMGame game, Player player) {
        this.game = game;
        this.player = player;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        font = Config.createFont(32);
        font.setColor(1f, 1f, 1, 1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        renderKnownAbilities();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Should end up back at the zone screen in most cases
            this.game.getScreenManager().popScreen();
        }

    }

    private void renderKnownAbilities() {
        batch.begin();

        if (this.player.getKnownAbilities().isEmpty()) {
            font.draw(batch, "You have no abilities", camera.position.x, camera.position.y);
        }
        ListIterator<Ability> iterator = this.player.getKnownAbilities().listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            Ability ability = iterator.next();
            font.draw(batch, ability.getName().getName(), camera.position.x, camera.position.y + font.getLineHeight() * (index + 2));
            font.draw(batch, ability.getDescription().getDescription(), camera.position.x, camera.position.y + font.getLineHeight() * (index + 1));
        }
        batch.end();

        // Handle input
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            player.setNextAction(new AbilityAction(this.player, this.player.getKnownAbilities().get(0)));
            game.requireTarget();
            // Pop back to ZoneScreen
            game.getScreenManager().popScreen();
        }

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

    }

    @Override
    public void dispose() {

    }
}
