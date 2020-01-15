package com.jscisco.lom.ui.kingdom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.ui.kingdom.shared.HeroInfoBlock;
import com.jscisco.lom.ui.screens.AbstractSelectionScreen;
import com.jscisco.lom.ui.screens.ZoneScreen;
import com.jscisco.lom.util.Size3D;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class HireHeroScreen extends AbstractSelectionScreen<HeroInfoBlock> {

    private static final Logger logger = LoggerFactory.getLogger(HireHeroScreen.class);

    private LOMGame game;
    private List<Player> heroes;
    private List<HeroInfoBlock> infoBlocks;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private int selectedHero;

    public HireHeroScreen(LOMGame game, List<Player> heroes) {
        this.game = game;
        this.heroes = heroes;

        this.infoBlocks = new ArrayList<>();
        ListIterator<Player> iterator = this.heroes.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            this.infoBlocks.add(new HeroInfoBlock(iterator.next(), 25, 100 * index, 300, 100));
        }

        this.selectedHero = this.infoBlocks.size() - 1;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        this.infoBlocks.get(selectedHero).select();

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

        for (HeroInfoBlock infoBlock : this.infoBlocks) {
            infoBlock.render(batch);
        }
    }

    private Player getSelectedHero() {
        return this.choices.get(this.currentSelectionIdx).getValue();
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

    @Override
    public void handleSelection() {
        Zone zone = new Zone(new Size3D(50, 120, 3), getSelectedHero());
        this.game.getScreenManager().setScreen(new ZoneScreen(this.game, zone));
    }
}
