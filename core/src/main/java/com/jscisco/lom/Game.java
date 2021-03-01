package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jscisco.lom.application.GameScreen;
import com.jscisco.lom.application.Textures;

public class Game extends ApplicationAdapter {
    Screen screen;

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void create() {
        // Initialize everything
        Textures.initialize();
//        setScreen(new TitleScreen(this));
        setScreen(new GameScreen(this));
        screen.show();
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

}
