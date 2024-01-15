package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.Game;
import com.jscisco.lom.raws.RawMaster;
import com.jscisco.lom.services.ZoneService;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.zone.Zone;
import com.jscisco.lom.map.*;
import com.jscisco.lom.persistence.GameVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.FakeLanguageGen;
import squidpony.squidmath.RNG;

public class TitleScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(TitleScreen.class);
    OrthographicCamera camera = new OrthographicCamera();
    ZoneService zoneService;
    ObjectMapper objectMapper;

    RawMaster raws;

    public TitleScreen(Game game) {
        super(game);

        // TODO: IOC?
        zoneService = ServiceLocator.getBean(ZoneService.class);
        objectMapper = ServiceLocator.getBean(ObjectMapper.class);
        GameVersion gv = ServiceLocator.getBean(GameVersion.class);
        raws = ServiceLocator.getBean(RawMaster.class);

        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        Table table = new Table();
        table.top();
        table.defaults().pad(10f);
        table.setFillParent(true);

        TextButton newGame = new TextButton("New Game", skin, "default");
        newGame.setWidth(100f);
        newGame.setHeight(50f);

        TextButton loadGame = new TextButton("Load Game", skin, "default");
        loadGame.setWidth(100f);
        loadGame.setHeight(50f);

        TextButton quickstart = new TextButton("Quick Start", skin, "default");

        TextButton outputLevel = new TextButton("Output Level", skin, "default");

        TextButton quitGame = new TextButton("Quit", skin, "default");
        quitGame.setWidth(100f);
        quitGame.setHeight(50f);

        TextButton saveGameTest = new TextButton("Save Game Test", skin, "default");
        saveGameTest.setWidth(100f);
        saveGameTest.setHeight(100f);

        TextButton mapGenTest = new TextButton("Map Generation Test", skin, "default");
        mapGenTest.setWidth(100f);
        mapGenTest.setHeight(100f);

//        table.add(newGame);
//        table.row();
        table.add(quickstart);
        table.row();
        table.add(outputLevel);
//        table.row();
//        table.add(loadGame);
//        table.row();
        table.add(quitGame);
        table.row();
//        table.add(saveGameTest);
//        table.row();
        table.add(mapGenTest);

        mapGenTest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BuilderChain chain = new BuilderChain(1, 100, 100);
                chain.startWith(new DebugStarterBuilder());
                chain.with(new RoomBasedStartingPosition());
                chain.with(new RoomBasedSpawner());
                chain.build(new RNG(), raws);

                game.setScreen(new DebugLevelScreen(game, chain));
                dispose();
                game.getScreen().show();
            }
        });

        quickstart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Kingdom kingdom = new Kingdom(Name.of(FakeLanguageGen.FANTASY_NAME.word(true)));
                // Create the zone
                Zone zone = zoneService.createZone(5);
                Level level = zone.getLevels().getFirst();
                BuildData buildData = zoneService.getBuildDataAtDepth(1);
                Hero hero = EntityFactory.player();
                hero.setPosition(buildData.getStartingPosition());
                logger.info("" + hero.getPosition());
                hero.setLevel(level);

                game.setScreen(new GameScreen(game, hero));
                dispose();
                game.getScreen().show();
            }
        });

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        quitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
