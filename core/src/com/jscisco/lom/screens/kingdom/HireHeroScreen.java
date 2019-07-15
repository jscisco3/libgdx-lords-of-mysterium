package com.jscisco.lom.screens.kingdom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.screens.kingdom.shared.HeroInfoBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class HireHeroScreen implements Screen {

    private Game game;
    private List<Player> heroes;
    private List<HeroInfoBlock> infoBlocks;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public HireHeroScreen(Game game, List<Player> heroes) {
        this.game = game;
        this.heroes = heroes;

        this.infoBlocks = new ArrayList<>();
        ListIterator<Player> iterator = this.heroes.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            this.infoBlocks.add(new HeroInfoBlock(iterator.next(), 25, 50 * index, Config.WINDOW_WIDTH, 100));
        }

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        for (HeroInfoBlock infoBlock : this.infoBlocks) {
            infoBlock.render(batch);
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
