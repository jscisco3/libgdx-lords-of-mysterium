package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.application.ui.Block;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

public class LoadGameScreen extends AbstractScreen {

    private static final Logger logger = LoggerFactory.getLogger(LoadGameScreen.class);

    private final Table content;
    private final ScrollPane scroller;
    private final List<SaveGame> savedGames;
    private final GameRepository gameRepository;


    public LoadGameScreen(Game game, List<SaveGame> savedGames) {
        super(game);
        this.savedGames = savedGames;
        stage.setDebugAll(false);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        gameRepository = ctx.getBean(GameRepository.class);

        logger.info(MessageFormat.format("We have {0} games to choose from.", this.savedGames));

        content = new Table(GameConfiguration.getSkin());
        content.setFillParent(true);
        scroller = new ScrollPane(content);

        scroller.setFadeScrollBars(false);

        setContent();

        stage.addActor(content);
        Gdx.input.setInputProcessor(stage);
    }

    void setContent() {
        // Back button
        TextButton back = new TextButton("Back", GameConfiguration.getSkin(), "default");
        back.setWidth(100f);
        back.setHeight(50f);

        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TitleScreen(game));
            }
        });

        content.add(back);

        for (SaveGame saveGame : this.savedGames) {
            SavedGameBlock block = new SavedGameBlock(saveGame);
            block.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    saveGame.setLastPlayed(LocalDateTime.now());
                    gameRepository.save(saveGame);
                    game.setScreen(new KingdomScreen(game, saveGame.getKingdom()));
                }
            });
            content.add(block);
            content.row();
        }
    }


    private static class SavedGameBlock extends Block {
        private final SaveGame saveGame;
        private final Skin skin = GameConfiguration.getSkin();
        private final Table table = new Table(skin);

        public SavedGameBlock(SaveGame saveGame) {
            this.saveGame = saveGame;

            Container<Table> container = new Container<>();
            container.setSize(300f, 100f);
            container.setActor(table);

            table.setFillParent(true);

            // Initialize table
            Label name = new Label(saveGame.getKingdom().getName().getName(), skin, "default");
            table.add(name).top().center();
            table.row();

            Label lastPlayed = new Label(saveGame.getLastPlayed().toString(), skin, "default");
            table.add(lastPlayed).top().center();

            this.addActor(table);
            this.setSize(container.getWidth(), container.getHeight());
        }
    }


}
