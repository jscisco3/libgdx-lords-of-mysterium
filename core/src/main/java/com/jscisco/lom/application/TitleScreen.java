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
import com.jscisco.lom.Game;
import com.jscisco.lom.application.services.GameService;
import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.EntityFactory;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.FakeLanguageGen;

import java.time.Instant;

public class TitleScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(TitleScreen.class);
    OrthographicCamera camera = new OrthographicCamera();
    GameService gameService;
    ZoneService zoneService;


    public TitleScreen(Game game) {
        super(game);

        gameService = ServiceLocator.getBean(GameService.class);
        zoneService = ServiceLocator.getBean(ZoneService.class);

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


        table.add(newGame);
        table.row();
        table.add(quickstart);
        table.row();
        table.add(outputLevel);
        table.row();
        table.add(loadGame);
        table.row();
        table.add(quitGame);

        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new NewGameScreen(game));
                game.getScreen().show();
            }
        });

        quitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                Gdx.app.exit();
            }
        });

        quickstart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Create the save game
                SaveGame saveGame = new SaveGame();
                // Create the kingdom
                Kingdom kingdom = new Kingdom(Name.of(FakeLanguageGen.FANTASY_NAME.word(true)));
                saveGame.setKingdom(kingdom);
                // Create the zone
                Zone zone = zoneService.createZone(5);
                saveGame.addZone(zone);

                Level level = zone.getLevels().get(0);
                Hero hero = EntityFactory.player();
//                AIState state = new AIState(hero);
//                state.setController(new PlayerHunterSeekerAI(hero));
//                hero.setState(state);

                level.addEntityAtPosition(hero, Position.of(1, 1));

                logger.debug("Level id: " + level.getId());

                // Add some items;
                for (int i = 0; i < 5; i++) {
                    Item item = new Item.Builder()
                            .withName(Name.of("Sword"))
                            .withGlyph(Assets.sword)
                            .build();

                    level.addItemAtPosition(item, Position.of(i + 3, 5));
                }

                // Set the screen with the correct level.
                // The level can get the hero, so we do not need to pass it in
                game.setScreen(new GameScreen(game, saveGame, hero));
                dispose();
                game.getScreen().show();
            }
        });

        outputLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Level level = new Level(80, 40, new LevelGeneratorStrategy.CellularAutomataStrategy());
                String filename = "test" + Instant.now().toString() + ".txt";
                LevelOutputToFile.outputToFile(level, filename);
            }
        });

        loadGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game, gameService.getGames()));
                dispose();
                game.getScreen().show();
            }
        });

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
