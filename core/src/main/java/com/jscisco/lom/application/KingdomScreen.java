package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.Game;

public class KingdomScreen extends AbstractScreen {

    public KingdomScreen(Game game) {
        super(game);
        Textures.kingdomBackground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(Textures.kingdomBackground, 0, 0, GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);
        batch.end();
    }
}