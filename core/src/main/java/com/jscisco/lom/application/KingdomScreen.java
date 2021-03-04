package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.Game;

public class KingdomScreen extends AbstractScreen {

    private Texture backgroundTexture;

    public KingdomScreen(Game game) {
        super(game);
        backgroundTexture = game.getAssets().getTexture(Assets.background);
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);
        batch.end();
    }
}