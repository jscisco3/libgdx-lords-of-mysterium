package com.jscisco.lom.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Tile;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ZoneScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(ZoneScreen.class);

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
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
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
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        updateCamera();
        camera.update();

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        drawSeenTiles();
        drawFOVTiles();
        drawItems();
        drawEntities();
        drawPlayerHUD();

        zone.getCurrentStage().process();
        zone.getCurrentState().handleInput(Gdx.input);

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            this.game.setScreen(new InventoryScreen(this.game, this.zone));
        }

        zone.getCurrentState().update();
        zone.getCurrentStage().updateTilesBasedOnFOV();
        logger.debug("Render calls: " + batch.renderCalls);
        logger.debug("Frames per second: " + Gdx.graphics.getFramesPerSecond());
    }

    private void drawSeenTiles() {
        Tile[][] tiles = this.zone.getCurrentStage().getTiles();
        Tile tile;

        batch.begin();
        batch.setColor(0.5f, 0.5f, 0.5f, 1.0f);
        for (int x = 0; x < this.zone.getCurrentStage().getWidth(); x++) {
            for (int y = 0; y < this.zone.getCurrentStage().getHeight(); y++) {
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
        for (int x = 0; x < this.zone.getCurrentStage().getWidth(); x++) {
            for (int y = 0; y < this.zone.getCurrentStage().getHeight(); y++) {
                tile = tiles[x][y];
                if (tile.isInFov()) {
                    batch.draw(tile.getTerrain().getTexture(), x * 24.0f, y * 24.0f);
                }
            }
        }
        batch.end();
    }

    private void drawItems() {
        batch.begin();

        for (Item item : this.zone.getCurrentStage().getItems()) {
            if (this.zone.getCurrentStage().getTileAt(item.getPosition()).isInFov()) {
                batch.draw(item.getTexture(), item.getPosition().getX() * 24.0f, item.getPosition().getY() * 24.0f);
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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Sidebar
        shapeRenderer.setColor(Config.SIDEBAR_COLOR);
        shapeRenderer.rect(sidebarX(), sidebarY(), Config.SIDEBAR_WIDTH, Config.SIDEBAR_HEIGHT);

        // Log Area
        shapeRenderer.setColor(Config.LOG_AREA_COLOR);
        shapeRenderer.rect(logX(), logY(), Config.LOG_AREA_WIDTH, Config.LOG_AREA_HEIGHT);

        shapeRenderer.end();

        drawPlayerStats();
    }

    /**
     * Calculate bottom left x coordinate of log area
     *
     * @return bottom left x coordinate of log area
     */
    private float logX() {
        return camera.position.x - (Config.WINDOW_WIDTH / 2f);
    }

    /**
     * Calculate bottom left y coordinate of log area
     *
     * @return bottom left y coordinate of log area
     */
    private float logY() {
        return camera.position.y - (Config.WINDOW_HEIGHT / 2f);
    }

    /**
     * Calculate bottom left x coordinate of sidebar
     *
     * @return bottom right y coordinate of sidebar
     */
    private float sidebarX() {
        return camera.position.x + (Config.WINDOW_WIDTH / 2f - Config.SIDEBAR_WIDTH);
    }

    /**
     * Calculate bottom right y coordinate of sidebar
     *
     * @return bottom right y coordinate of sidebar
     */
    private float sidebarY() {
        return camera.position.y - (Config.WINDOW_HEIGHT / 2f);
    }

    private void drawPlayerStats() {
        batch.begin();

        List<String> stats = new ArrayList<>();
        stats.add(player.getName().get());
        stats.add(String.format("Health: %s/%s", player.getHealth().getHp(), player.getHealth().getMaxHP()));
        stats.add(String.format("Strength: %s", player.getStats().getStrength()));
        stats.add(String.format("Intelligence: %s", player.getStats().getIntelligence()));
        stats.add(String.format("Dexterity: %s", player.getStats().getDexterity()));
        stats.add(String.format("Constitution: %s", player.getStats().getConstitution()));

        ListIterator<String> iterator = stats.listIterator();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            font.draw(batch, iterator.next(), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - (5 + 20 * index));
        }

//        font.draw(batch, String.format("Strength: %s", player.getStats().getStrength()), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - 25);
//        font.draw(batch, String.format("Intelligence: %s", player.getStats().getIntelligence()), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - 45);
//        font.draw(batch, String.format("Dexterity: %s", player.getStats().getDexterity()), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - 65);
//        font.draw(batch, String.format("Constitution: %s", player.getStats().getConstitution()), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - 85);
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

    private void updateCamera() {
        // TODO: Figure out why this is weird with small stages
        Position position = player.getPosition();
        float maxWidth = (zone.getWidth() * 24.0f) - (Config.WINDOW_WIDTH / 2.0f);
        float maxHeight = (zone.getHeight() * 24.0f) - (Config.WINDOW_HEIGHT / 2.0f);

        // Set it to player X * 24.0f, then clamp it?
        camera.position.x = MathUtils.clamp((position.getX() + 2) * 24.0f, Config.WINDOW_WIDTH / 2.0f, maxWidth + Config.SIDEBAR_WIDTH);
        camera.position.y = MathUtils.clamp(position.getY() * 24.0f, Config.WINDOW_HEIGHT / 2.0f - Config.LOG_AREA_HEIGHT, maxHeight);
        logger.trace(String.format("New camera position: %s", camera.position.toString()));
    }
}