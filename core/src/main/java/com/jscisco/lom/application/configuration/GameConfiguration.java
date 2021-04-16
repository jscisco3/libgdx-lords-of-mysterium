package com.jscisco.lom.application.configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.common.eventbus.EventBus;
import com.jscisco.lom.domain.GameLog;

import java.util.Random;

public class GameConfiguration {

    public static int SCREEN_WIDTH = 1280;
    public static int SCREEN_HEIGHT = 900;

    public static Skin getSkin() {
        return new Skin(Gdx.files.internal("skins/uiskin.json"));
    }

    public static final GameLog gameLog = new GameLog();
    public static final EventBus eventBus = new EventBus();

    public static final Random random = new Random(0xDEADBEEF);

    public static void configureEventBus() {
        eventBus.register(gameLog);
    }
}