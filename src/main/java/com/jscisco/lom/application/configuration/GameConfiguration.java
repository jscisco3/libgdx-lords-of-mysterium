package com.jscisco.lom.application.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.GameLog;
import squidpony.squidmath.StatefulRNG;

import java.util.Random;

public class GameConfiguration {

    public static int SCREEN_WIDTH = 1280;
    public static int SCREEN_HEIGHT = 900;

    public static Skin getSkin() {
        return new Skin(Gdx.files.internal("skins/uiskin.json"));
    }

    public static final GameLog gameLog = new GameLog();

    public static final Random random = new Random(0xDEADBEEF);
    public static final StatefulRNG rng = new StatefulRNG(0xDEADBEEF);
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void configureEventBus() {
        // eventBus.register(gameLog);
    }

}
