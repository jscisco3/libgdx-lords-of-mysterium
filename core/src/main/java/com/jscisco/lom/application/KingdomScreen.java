package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.repository.GameRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KingdomScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(KingdomScreen.class);

    private Kingdom kingdom;
    private Texture backgroundTexture;

    // UI Elements
    private final Label kingdomName;
    private Image inn;
    private Image portal;

    public KingdomScreen(Game game, Kingdom kingdom) {
        super(game);
        this.kingdom = kingdom;

        backgroundTexture = game.getAssets().getTexture(Assets.background);
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        kingdomName = new Label(kingdom.getName().getName(), GameConfiguration.getSkin(), "default");
        kingdomName.setPosition(0, GameConfiguration.SCREEN_HEIGHT - kingdomName.getHeight());

        inn = new Image(game.getAssets().getTexture(Assets.inn));
        inn.setPosition(250f, 400f);

        portal = new Image(game.getAssets().getTexture(Assets.portal));
        portal.setPosition(600f, 200f);
        portal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logger.info("Clicked portal");
                Zone zone = new Zone(3);
                Level level = zone.getLevels().get(0);
                Hero hero = EntityFactory.player();
                level.addEntityAtPosition(hero, level.getEmptyTile(hero));
                game.setScreen(new GameScreen(game, new SaveGame(), level, hero));
            }
        });

        stage.addActor(portal);
        stage.addActor(inn);
        stage.addActor(kingdomName);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        // TODO: Can this just be added to the stage?
        batch.draw(backgroundTexture, 0, 0, GameConfiguration.SCREEN_WIDTH, GameConfiguration.SCREEN_HEIGHT);
        batch.end();
        stage.draw();
    }
}