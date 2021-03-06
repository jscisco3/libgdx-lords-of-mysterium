package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jscisco.lom.Game;
import com.jscisco.lom.domain.kingdom.Kingdom;

public class KingdomScreen extends AbstractScreen {

    private Kingdom kingdom;
    private Texture backgroundTexture;

    // UI Elements
    private final Label kingdomName;

    public KingdomScreen(Game game, Kingdom kingdom) {
        super(game);
        this.kingdom = kingdom;
        backgroundTexture = game.getAssets().getTexture(Assets.background);
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        kingdomName = new Label(kingdom.getName().getName(), GameConfiguration.getSkin(), "default");
        kingdomName.setPosition(0, GameConfiguration.SCREEN_HEIGHT - kingdomName.getHeight());

        stage.addActor(kingdomName);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);
        batch.end();
        stage.draw();
    }
}