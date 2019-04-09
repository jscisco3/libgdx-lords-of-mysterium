package com.jscisco.lom.screens;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.components.Tile;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(DungeonScreen.class);

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<Tile> mTile;
    private EntitySubscription subscription;

    private Stage stage;
    private Dungeon dungeon;


    public DungeonScreen() {
        stage = new Stage();
        dungeon = new Dungeon(new Size3D(20, 20, 1));
        dungeon.getWorld().inject(this);
        stage.addActor(dungeon);

        subscription = dungeon.getWorld().getAspectSubscriptionManager().get(Aspect.all(Tile.class));
        logger.info("Actors before adding tiles: " + stage.getActors().size);

        for (int e : subscription.getEntities().getData()) {
            stage.addActor(mTile.get(e).actor);
        }

        logger.info("Actors after adding tiles: " + stage.getActors().size);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        dungeon.getCurrentState().handleInput(Gdx.input);
        dungeon.getCurrentState().update();

//        logger.info("Frames per second: " + Gdx.graphics.getFramesPerSecond());

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
