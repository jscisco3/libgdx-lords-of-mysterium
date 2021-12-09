package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.application.services.GameService;
import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KingdomScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(KingdomScreen.class);

    private Kingdom kingdom;
    private TextureRegion backgroundTextureRegion;
    private Texture backgroundTexture;

    private GameService gameService;
    private ZoneService zoneService;

    // UI Elements
    private final Label kingdomName;
    private Image inn;
    private Image portal;

    public KingdomScreen(Game game, Kingdom kingdom) {
        super(game);
        this.kingdom = kingdom;
        backgroundTextureRegion = game.getAssets().getTextureRegion(Assets.background);

        Pixmap backgroundPixmap = new Pixmap(backgroundTextureRegion.getRegionWidth(), backgroundTextureRegion.getRegionHeight(), Pixmap.Format.RGBA8888);
        backgroundTexture = new Texture(backgroundPixmap);
        backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        this.gameService = ServiceLocator.getBean(GameService.class);
        this.zoneService = ServiceLocator.getBean(ZoneService.class);

        kingdomName = new Label(kingdom.getName().getName(), GameConfiguration.getSkin(), "default");
        kingdomName.setPosition(0, GameConfiguration.SCREEN_HEIGHT - kingdomName.getHeight());

        inn = new Image(game.getAssets().getTextureRegion(Assets.inn));
        inn.setPosition(250f, 400f);

        portal = new Image(game.getAssets().getTextureRegion(Assets.portal));
        portal.setPosition(600f, 200f);
        portal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logger.info("Clicked portal");
                Zone zone = zoneService.createZone();
                Level level = zoneService.createLevel(zone.getId(), 100, 100, LevelGeneratorStrategy.Strategy.EMPTY);
                Hero hero = EntityFactory.player();
                level.addEntityAtPosition(hero, level.getEmptyTile(hero));
                zoneService.saveLevel(level);
                // TODO: Necessary?
                game.setScreen(new GameScreen(game, hero));
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
