package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameConfiguration {

    public static int SCREEN_WIDTH = 1280;
    public static int SCREEN_HEIGHT = 900;

    public static Skin getSkin() {
        return new Skin(Gdx.files.internal("skins/uiskin.json"));
    }

}
