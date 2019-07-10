package com.jscisco.lom;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.screens.ZoneScreen;
import com.jscisco.lom.util.Size3D;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.RNG;

public class LOMGame extends Game {

    private Logger logger = LoggerFactory.getLogger(LOMGame.class);
    private ZoneScreen zoneScreen;

    public static final RNG rng = new RNG(0xDEADBEEF);

    @Override
    public void create() {
        Assets.load();
        zoneScreen = new ZoneScreen(this, new Zone(new Size3D(100, 80, 1)));
        setScreen(zoneScreen);
//        setScreen(new MainMenuScreen(this));

    }

    @Override
    public void render() {
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
