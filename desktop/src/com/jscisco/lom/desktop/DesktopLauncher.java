package com.jscisco.lom.desktop;

import ch.qos.logback.classic.util.ContextInitializer;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.jscisco.lom.game.LOMGame;


public class DesktopLauncher {
    public static void main(String[] arg) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 512;
        settings.maxHeight = 512;

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 512;
        config.height = 512;
        config.forceExit = true;

        TexturePacker.process(settings, "./img/", "../out/images", "images");

        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "../../logback.xml");
        new LwjglApplication(new LOMGame(), config);
    }
}
