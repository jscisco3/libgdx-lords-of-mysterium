package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jscisco.lom.Game;

public class TitleScreen extends AbstractScreen {

    Stage stage;
    OrthographicCamera camera = new OrthographicCamera();

    public TitleScreen(Game game) {
        super(game);
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

        TextButton kingdomScreen = new TextButton("Kingdom", skin, "default");

        TextButton quitGame = new TextButton("Quit", skin, "default");
        quitGame.setWidth(100f);
        quitGame.setHeight(50f);


        table.add(newGame);
        table.row();
        table.add(kingdomScreen);
        table.row();
        table.add(loadGame);
        table.row();
        table.add(quitGame);

        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
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

        kingdomScreen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new KingdomScreen(game));
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
        batch.begin();
        stage.draw();
        batch.end();
    }
}
