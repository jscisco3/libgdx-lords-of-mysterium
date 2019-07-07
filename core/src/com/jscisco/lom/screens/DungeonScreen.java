package com.jscisco.lom.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.dungeon.Block;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(DungeonScreen.class);

    private Dungeon dungeon;
    private Game game;
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private BitmapFont font;

    public DungeonScreen(Game game, Dungeon dungeon) {
        this.game = game;
        this.dungeon = dungeon;
//        dungeon = new Dungeon(new Size3D(100, 80, 1));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, LOMGame.WIDTH, LOMGame.HEIGHT);
        font = new BitmapFont();
        font.setColor(255, 0, 0, 1);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateCamera();
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        Block[][][] blocks = dungeon.getBlocks();
        Block block;

        batch.begin();
        batch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                block = blocks[x][y][dungeon.getPlayer().getZ()];
                if (block.isSeen()) {
                    batch.draw(block.getTerrain().getTexture(), x * 24.0f, y * 24.0f);
                }
            }
        }
        font.draw(batch, String.format("FPS: %s", Gdx.graphics.getFramesPerSecond()), camera.position.x - 300, camera.position.y + 200);
        font.draw(batch, String.format("Position: {%s, %s}", dungeon.getPlayer().getPosition().getX(), dungeon.getPlayer().getPosition().getY()), camera.position.x - 300, camera.position.y + 250);
        batch.end();

        batch.begin();
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                block = blocks[x][y][dungeon.getPlayer().getZ()];
                if (block.isInFov()) {
                    batch.draw(block.getTerrain().getTexture(), x * 24.0f, y * 24.0f);
                }
            }
        }
        for (Actor a : dungeon.getActors()) {
            batch.draw(a.getTexture(), a.getX() * 24.0f, a.getY() * 24.0f);
        }
        batch.end();

        process();
        dungeon.getCurrentState().handleInput(Gdx.input);

        dungeon.getCurrentState().update();
        dungeon.updateBlocksBasedOnFOV();
        logger.debug("Render calls: " + batch.renderCalls);
        logger.debug("Frames per second: " + Gdx.graphics.getFramesPerSecond());
    }

    /**
     * This method processes the turns for all actors in the dungeon level
     */
    private void process() {
        for (Actor actor : dungeon.getActors()) {
            Command command = actor.getNextCommand();
            if (command != null) {
                command.invoke();
            }
        }
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

    public void updateCamera() {
        Position3D position = dungeon.getPlayer().getPosition();
        float maxWidth = (dungeon.getWidth() * 24.0f) - (LOMGame.WIDTH / 2.0f);
        float maxHeight = (dungeon.getHeight() * 24.0f) - (LOMGame.HEIGHT / 2.0f);

        // Set it to player X * 24.0f, then clamp it?
        camera.position.x = MathUtils.clamp(position.getX() * 24.0f, LOMGame.WIDTH / 2.0f, maxWidth);
        camera.position.y = MathUtils.clamp(position.getY() * 24.0f, LOMGame.HEIGHT / 2.0f, maxHeight);
        logger.trace(String.format("New camera position: %s".format(camera.position.toString())));
    }
}