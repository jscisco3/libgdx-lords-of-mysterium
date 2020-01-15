package com.jscisco.lom.ui.screens;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.LOMGame;

import java.util.ArrayList;
import java.util.List;

public class ScreenManager {

    private LOMGame game;
    private List<Screen> screens;

    public ScreenManager(LOMGame game) {
        this.game = game;
        this.screens = new ArrayList<>();
    }

    public void pushScreen(Screen screen) {
        this.screens.add(screen);
        this.game.setScreen(screen);
    }

    public void popScreen() {
        assert this.screens.size() > 1;
        this.screens.remove(this.screens.size() - 1);
        this.game.setScreen(this.screens.get(this.screens.size() - 1));
    }

    public void setScreen(Screen screen) {
        this.screens.set(this.screens.size() - 1, screen);
        this.game.setScreen(this.screens.get(this.screens.size() - 1));
    }
}
