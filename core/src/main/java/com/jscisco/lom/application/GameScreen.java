package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.Game;

public class GameScreen extends AbstractScreen {

    Stage stage = new Stage();

    public GameScreen(Game game) {
        super(game);
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        TextButton button = new TextButton("New Game", skin, "default");
        button.setWidth(100f);
        button.setHeight(50f);
        button.setPosition(Gdx.graphics.getWidth() / 2f - 100f, Gdx.graphics.getHeight() / 2f - 50f);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                button.setText("You clicked the button");
            }
        });

        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);

        batch.begin();
        stage.draw();
        batch.end();
    }
}
