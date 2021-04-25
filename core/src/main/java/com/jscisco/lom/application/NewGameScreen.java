package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.application.ui.HeroBlock;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.kingdom.Kingdom;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.FakeLanguageGen;

import java.util.ArrayList;
import java.util.List;

/**
 * ____________________
 * |                   \
 * |  Name: ====       \
 * |                   \
 * |         ______    \
 * |         |next|    \
 * |         -----     \
 * |                   \
 * |___________________\
 */

public class NewGameScreen extends AbstractScreen {

    private final Table chooseHeroTable = new Table();
    private final List<Hero> heroes = new ArrayList<Hero>();
    private final Table heroChoice;

    private final TextArea input = new TextArea(FakeLanguageGen.FANTASY_NAME.word(true), GameConfiguration.getSkin(), "default");
    private final TextButton next = new TextButton("Start Game", GameConfiguration.getSkin(), "default");


    private static final Logger logger = LoggerFactory.getLogger(NewGameScreen.class);

    public NewGameScreen(Game game) {
        super(game);

        stage.setDebugAll(true);

        setUpKingdomNameTable();
        generateHeroes();


        // Hero summary
        heroChoice = createHeroChoiceTable();
        heroChoice.setVisible(false);
        heroChoice.setPosition(0f, 0f);

        stage.addActor(heroChoice);

        Gdx.input.setInputProcessor(stage);
    }

    private void setUpKingdomNameTable() {
        Container<Table> container = new Container<>();
        // Table
        // Which contains two tables
        Table topTable = new Table(GameConfiguration.getSkin());
        topTable.setFillParent(true);

        // Table which contains the label and text area
        Table kingdomNameTable = new Table();

        Label label = new Label("Name your kingdom: ", GameConfiguration.getSkin(), "default");
        kingdomNameTable.add(label).left().padRight(10f);
        stage.setKeyboardFocus(input);
        kingdomNameTable.add(input);

        // Table for next button
        Table bottomTable = new Table();
        next.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new KingdomScreen(game, new Kingdom(Name.of(input.getText()))));
            }
        });
        bottomTable.add(next);

        topTable.add(kingdomNameTable).top().center();
        topTable.add(bottomTable).bottom().right();

        container.setSize(GameConfiguration.SCREEN_WIDTH * 0.75f, GameConfiguration.SCREEN_HEIGHT * 0.5f);
        // TODO: Center
        container.setPosition(0f, 0f);
        container.fillX();
        container.setActor(topTable);

        stage.addActor(container);
    }

    private void generateHeroes() {
        for (int i = 0; i < 10; i++) {
            heroes.add(new Hero.Builder()
                    .withName(Name.of(String.valueOf(i)))
                    .withGlyph(Assets.warrior)
                    .build());
        }
    }

    private Table createHeroChoiceTable() {
        Table table = new Table(GameConfiguration.getSkin());
        for (Hero p : heroes) {
            table.add(new HeroBlock(p)).top();
//            table.add(new Label(p.getName().getName(), table.getSkin(), "default")).top().center();
            table.row();
        }
        Table scrollTable = new Table(GameConfiguration.getSkin());
        scrollTable.setFillParent(true);
        ScrollPane scroller = new ScrollPane(table, GameConfiguration.getSkin());
        scroller.setFadeScrollBars(false);
        float width = 350f;
        scrollTable.add(scroller)
                .width(width)
                .expandY();
        return scrollTable;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act();
        // Disable next button if there is no name for the kingdom
        next.setDisabled(StringUtils.isBlank(input.getText()));
    }
}
