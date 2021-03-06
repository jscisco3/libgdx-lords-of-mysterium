package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.ui.HeroBlock;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.EntityName;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.kingdom.Kingdom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final List<Player> heroes = new ArrayList<Player>();
    private final Table heroChoice;
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
        TextArea input = new TextArea("", GameConfiguration.getSkin(), "default");
        stage.setKeyboardFocus(input);
        kingdomNameTable.add(input);

        // Table for next button
        Table bottomTable = new Table();
        TextButton next = new TextButton("Start Game", GameConfiguration.getSkin(), "default");
        next.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                container.addAction(Actions.moveTo(GameConfiguration.SCREEN_WIDTH, 0, 0.1f));
//                heroChoice.setVisible(true);
//                stage.setScrollFocus(heroChoice);
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
            heroes.add(new Player.Builder()
                    .withName(EntityName.of(String.valueOf(i)))
                    .withAsset(Assets.warrior)
                    .build());
        }
    }

    private Table createHeroChoiceTable() {
        Table table = new Table(GameConfiguration.getSkin());
        for (Player p : heroes) {
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
    }
}
