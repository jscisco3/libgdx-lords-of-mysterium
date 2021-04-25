package com.jscisco.lom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Lords of Mysterium";
        config.width = GameConfiguration.SCREEN_WIDTH;
        config.height = GameConfiguration.SCREEN_HEIGHT;

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;

        TexturePacker.process(settings, "../../assets/textures/", "packed/", "assets");

        new LwjglApplication(new Game(), config);
    }
}
