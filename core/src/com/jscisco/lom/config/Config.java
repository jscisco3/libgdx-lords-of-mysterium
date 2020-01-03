package com.jscisco.lom.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Config {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 900;

    public static final int SIDEBAR_HEIGHT = WINDOW_HEIGHT;
    public static final int SIDEBAR_WIDTH = 200;
    public static final int LOG_AREA_HEIGHT = 100;
    public static final int LOG_AREA_WIDTH = WINDOW_WIDTH - SIDEBAR_WIDTH;

    public static final int TILE_HEIGHT = 24;
    public static final int TILE_WIDTH = 24;

    public static final Color SIDEBAR_COLOR = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    public static final Color LOG_AREA_COLOR = new Color(1f, 1f, 1f, 0.75f);

    public static final Color SELECTED_ITEM_COLOR = new Color(1f, 0.8f, 0.25f, 1f);
    public static final Color DEFAULT_FONT_COLOR = new Color(1f, 0.5f, 0.25f, 1f);

    public static final BehaviorRepository repository = new BehaviorRepository();

//
    public static BitmapFont createFont(float dp) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("../../fonts/consola.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (dp * Gdx.graphics.getDensity());
        return generator.generateFont(parameter);
    }
}
