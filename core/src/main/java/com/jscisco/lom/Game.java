package com.jscisco.lom;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.jscisco.lom.application.KingdomScreen;
import com.jscisco.lom.application.LoadingScreen;
import com.jscisco.lom.application.Textures;

public class Game extends ApplicationAdapter {
    Screen screen;
    private AssetManager assetManager = new AssetManager();

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
        setScreen(new LoadingScreen(this));
//        setScreen(new GameScreen(this));
//        setScreen(new KingdomScreen(this));
//        setScreen(new TitleScreen(this));
        screen.show();
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
