package com.jscisco.lom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Lords of Mysterium";
        config.width = GameConfiguration.SCREEN_WIDTH;
        config.height = GameConfiguration.SCREEN_HEIGHT;
        new LwjglApplication(new Game(), config);
    }
}
