package com.jscisco.lom.application;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
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
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        config.setTitle("Lords of Mysterium");
        config.setWindowedMode(GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;

        Path textureAssets = Paths.get(".", "assets", "textures");
        TexturePacker.process(settings, textureAssets.toAbsolutePath().toString(), "packed/", "assets");
        TexturePacker.process(settings, "assets/textures/", "packed/", "assets");

        // Unlock FPS
        config.setIdleFPS(10);
        config.setForegroundFPS(60);
        config.useVsync(true);
        new Lwjgl3Application(new Game(), config);

    }
}
