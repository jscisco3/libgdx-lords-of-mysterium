package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jscisco.lom.Game;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.ActorFactory;
import com.jscisco.lom.domain.entity.Player;
import com.jscisco.lom.domain.zone.Level;

public class GameScreen extends AbstractScreen {

    Stage stage = new Stage();
    Level level;

    public GameScreen(Game game) {
        super(game);
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Create a zone and a stage
        level = new Level();
        Player p = ActorFactory.player();
        level.addEntityAtPosition(p, Position.of(1, 1));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        level.draw(batch);
        batch.begin();
        stage.draw();
        batch.end();
    }
}
