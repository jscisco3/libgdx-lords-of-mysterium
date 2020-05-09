package com.jscisco.lom.shelf.screens;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.shelf.LOMGame_Deprecated;

import java.util.ArrayList;
import java.util.List;

public class ScreenManager {

    private LOMGame_Deprecated game;
    private List<Screen> screens;

    public ScreenManager(LOMGame_Deprecated game) {
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
