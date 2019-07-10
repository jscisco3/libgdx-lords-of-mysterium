package com.jscisco.lom.config;

import com.badlogic.gdx.graphics.Color;

public class Config {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 900;

    public static final int SIDEBAR_HEIGHT = WINDOW_HEIGHT;
    public static final int SIDEBAR_WIDTH = 200;
    public static final int LOG_AREA_HEIGHT = 100;
    public static final int LOG_AREA_WIDTH = WINDOW_WIDTH - SIDEBAR_WIDTH;

    public static final Color SIDEBAR_COLOR = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    public static final Color LOG_AREA_COLOR = new Color(1f, 1f, 1f, 0.75f);
}
