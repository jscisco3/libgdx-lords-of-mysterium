package com.jscisco.lom.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Tile;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZoneScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(ZoneScreen.class);

    private static final int SIDEBAR_HEIGHT = LOMGame.HEIGHT;
    private static final int SIDEBAR_WIDTH = 200;
    private static final int LOG_AREA_HEIGHT = 200;
    private static final int LOG_AREA_WIDTH = LOMGame.WIDTH;

    private Zone zone;
    private Game game;
    private Player player;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private BitmapFont font;

    public ZoneScreen(Game game, Zone zone) {
        this.game = game;
        this.zone = zone;
        this.player = zone.getCurrentStage().getPlayer();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
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

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        drawSeenTiles();
        drawFOVTiles();
        drawEntities();
        drawPlayerHUD();

        zone.getCurrentStage().process();
        zone.getCurrentState().handleInput(Gdx.input);

        zone.getCurrentState().update();
        zone.getCurrentStage().updateBlocksBasedOnFOV();
        logger.debug("Render calls: " + batch.renderCalls);
        logger.debug("Frames per second: " + Gdx.graphics.getFramesPerSecond());
    }

    private void drawSeenTiles() {
        Tile[][] tiles = this.zone.getCurrentStage().getTiles();
        Tile tile;

        batch.begin();
        batch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                tile = tiles[x][y];
                if (tile.isSeen()) {
                    batch.draw(tile.getTerrain().getTexture(), x * 24.0f, y * 24.0f);
                }
            }
        }
        font.draw(batch, String.format("FPS: %s", Gdx.graphics.getFramesPerSecond()), camera.position.x - 300, camera.position.y + 200);
        font.draw(batch, String.format("Position: {%s, %s}", zone.getCurrentStage().getPlayer().getPosition().getX(), zone.getCurrentStage().getPlayer().getPosition().getY()), camera.position.x - 300, camera.position.y + 250);
        batch.end();
    }

    private void drawFOVTiles() {
        Tile[][] tiles = this.zone.getCurrentStage().getTiles();
        Tile tile;

        batch.begin();
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 80; y++) {
                tile = tiles[x][y];
                if (tile.isInFov()) {
                    batch.draw(tile.getTerrain().getTexture(), x * 24.0f, y * 24.0f);
                }
            }
        }
        batch.end();
    }

    private void drawEntities() {
        batch.begin();

        for (Entity entity : this.zone.getCurrentStage().getEntities()) {
            if (this.zone.getCurrentStage().getTileAt(entity.getPosition()).isInFov()) {
                batch.draw(entity.getTexture(), entity.getX() * 24.0f, entity.getY() * 24.0f);
            }
        }

        batch.end();
    }

    private void drawPlayerHUD() {

        // Bottom left coords for sidebar
        float sidebarX = camera.position.x + (LOMGame.WIDTH / 2 - SIDEBAR_WIDTH);
        float sidebarY = camera.position.y - (LOMGame.HEIGHT / 2);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        shapeRenderer.rect(sidebarX, sidebarY, SIDEBAR_WIDTH, SIDEBAR_HEIGHT);

        shapeRenderer.end();

        batch.begin();
        font.draw(batch, String.format("HP: %s/%s", player.getHealth().getHp(), player.getHealth().getMaxHP()), sidebarX + 5, sidebarY + SIDEBAR_HEIGHT - 5);
        batch.end();
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
        Position position = player.getPosition();
        float maxWidth = (zone.getWidth() * 24.0f) - (LOMGame.WIDTH / 2.0f);
        float maxHeight = (zone.getHeight() * 24.0f) - (LOMGame.HEIGHT / 2.0f);

        // Set it to player X * 24.0f, then clamp it?
        camera.position.x = MathUtils.clamp((position.getX() + 2) * 24.0f, LOMGame.WIDTH / 2.0f, maxWidth + SIDEBAR_WIDTH);
        camera.position.y = MathUtils.clamp(position.getY() * 24.0f, LOMGame.HEIGHT / 2.0f - LOG_AREA_HEIGHT, maxHeight);
        logger.trace(String.format("New camera position: %s".format(camera.position.toString())));
    }
}