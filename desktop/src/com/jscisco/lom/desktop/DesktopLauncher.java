package com.jscisco.lom.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DesktopLauncher {

    private static final Logger logger = LoggerFactory.getLogger(DesktopLauncher.class);

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Lords of Mysterium";
        config.width = GameConfiguration.SCREEN_WIDTH;
        config.height = GameConfiguration.SCREEN_HEIGHT;

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;

        Path textureAssets = Paths.get(".", "assets", "textures");
        TexturePacker.process(settings, textureAssets.toAbsolutePath().toString(), "packed/", "assets");

        // Unlock FPS
        config.backgroundFPS = 10;
        config.foregroundFPS = 600;
        config.vSyncEnabled = false;
        new LwjglApplication(new Game(), config);
    }
}
