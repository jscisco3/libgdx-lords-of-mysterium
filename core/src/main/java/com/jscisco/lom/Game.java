package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.application.LoadingScreen;

public class Game extends ApplicationAdapter {
    Screen screen;
    private Assets assets = new Assets();

    public void setScreen(Screen screen) {
        this.screen = screen;
        this.screen.show();
    }

    public Screen getScreen() {
        return screen;
    }

    @Override
    public void create() {
        // Initialize everything
        setScreen(new LoadingScreen(this));
//        setScreen(new GameScreen(this));
//        setScreen(new KingdomScreen(this));
//        setScreen(new TitleScreen(this));
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
    }

    public Assets getAssets() {
        return assets;
    }
}
