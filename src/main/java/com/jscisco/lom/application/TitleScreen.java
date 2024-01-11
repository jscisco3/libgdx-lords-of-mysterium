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
import com.jscisco.lom.application.services.GameService;
import com.jscisco.lom.application.services.KingdomService;
import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
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
    GameService gameService;
    ZoneService zoneService;
    KingdomService kingdomService;
    ObjectMapper objectMapper;

    public TitleScreen(Game game) {
        super(game);

        // TODO: IOC?
        gameService = ServiceLocator.getBean(GameService.class);
        zoneService = ServiceLocator.getBean(ZoneService.class);
        kingdomService = ServiceLocator.getBean(KingdomService.class);
        objectMapper = ServiceLocator.getBean(ObjectMapper.class);

        GameVersion gv = ServiceLocator.getBean(GameVersion.class);

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

        table.add(newGame);
        table.row();
        table.add(quickstart);
        table.row();
        table.add(outputLevel);
        table.row();
        table.add(loadGame);
        table.row();
        table.add(quitGame);
        table.row();
        table.add(saveGameTest);
        table.row();
        table.add(mapGenTest);

        // newGame.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // game.setScreen(new NewGameScreen(game));
        // game.getScreen().show();
        // }
        // });
        //
        // quitGame.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // dispose();
        // Gdx.app.exit();
        // }
        // });

        mapGenTest.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BuilderChain chain = new BuilderChain(1, 100, 100);
                chain.startWith(new DebugStarterBuilder());
                chain.with(new RoomBasedStartingPosition());
                chain.with(new RoomBasedSpawner());
                chain.build(new RNG());

                game.setScreen(new DebugLevelScreen(game, chain));
                dispose();
                game.getScreen().show();
            }
        });

        quickstart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Create the save game
                // Create the kingdom
                Kingdom kingdom = new Kingdom(Name.of(FakeLanguageGen.FANTASY_NAME.word(true)));
                // Create the zone
                Zone zone = zoneService.createZone(5);

                Level level = zone.getLevels().getFirst();
                Hero hero = EntityFactory.player();
                hero.setPosition(Position.of(1, 1));
                hero.setLevel(level);

                game.setScreen(new GameScreen(game, hero));
                dispose();
                game.getScreen().show();
            }
        });

        // outputLevel.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // Level level = new Level(80, 40, new LevelGeneratorStrategy.CellularAutomataStrategy());
        // String filename = "test" + Instant.now().toString() + ".txt";
        // LevelOutputToFile.outputToFile(level, filename);
        // }
        // });
        //
        // loadGame.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // try {
        // game.setScreen(new LoadGameScreen(game, gameService.getGames()));
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // dispose();
        // game.getScreen().show();
        // }
        // });
        //
        // saveGameTest.addListener(new ClickListener() {
        // @Override
        // public void clicked(InputEvent event, float x, float y) {
        // // Generate a game metadata
        // SaveGame saveGame = new SaveGame(GameVersion.of("1.0.0"));
        // // Save the metadata
        // gameService.saveGame(saveGame);
        // // Create a kingdom
        // Kingdom kingdom = new Kingdom(Name.of("Test Kingdom"));
        // // Save the kingdom
        // try {
        // kingdomService.saveKingdom(kingdom, saveGame);
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // }
        //
        // // Create a zone with two levels
        // Zone zone = zoneService.createZone(2);
        // // Save the zone and the two levels
        // try {
        // zoneService.saveZone(saveGame, zone);
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // }
        //
        // // Add the hero
        // Hero hero = EntityFactory.player();
        // zone.getLevels().getFirst().addEntityAtPosition(hero, Position.of(1, 1));
        //
        // try {
        // zoneService.saveZone(saveGame, zone);
        // } catch (IOException e) {
        // throw new RuntimeException(e);
        // }
        //
        // }
        // });

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
