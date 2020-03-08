package com.jscisco.lom.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.jscisco.lom.LOMGame;
import com.jscisco.lom.action.AbilityAction;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.log.Message;
import com.jscisco.lom.log.MessageElement;
import com.jscisco.lom.log.MessageLog;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Tile;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class ZoneScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(ZoneScreen.class);

    private Zone zone;
    private LOMGame game;
    private Player player;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private BitmapFont font;

    public ZoneScreen(LOMGame game, Zone zone) {
        this.game = game;
        this.zone = zone;
        this.player = zone.getCurrentStage().getPlayer();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        font = new BitmapFont();
        font.setColor(255, 0, 0, 1);
        this.game.getStateManager().add(new PlayerTurnState(this.game, this.player, this.zone));
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
        drawMouseHoverOverlay();
        drawItems();
        drawEntities();
        drawPlayerStats();
        drawMessageLog();
        // TODO: Can this be renamed?
        drawInfoBox();
        drawDebug();

        if (!game.isConfirmationRequired() && !game.isTargetRequired()) {
            zone.getCurrentStage().process();
            this.game.getCurrentState().handleInput(Gdx.input);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            this.game.getScreenManager().pushScreen(new InventoryScreen(this.game, this.zone));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            this.game.getScreenManager().pushScreen(new KnownAbilitiesScreen(this.game, this.player));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            logger.info(game.getCurrentState().toString());
        }

//        // Mouse
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
//            // Convert mouse position to tile
//            Position goal = getStagePositionFromMousePosition(Position.get(Gdx.input.getX(), Gdx.input.getY()));
//            this.game.pushState(new MoveToState(this.game, this.zone.getCurrentStage(), goal));
//        }

        if (game.isTargetRequired()) {
            drawMouseHoverOverlay();
            Optional<Position> target = handleTargetingInput();
            target.ifPresent(pos -> {
                game.targetNoLongerRequired();
                AbilityAction a = (AbilityAction) player.getNextAction();
                logger.info("Ability Action: {}", a);
                logger.info("Ability Action Ability: {}", a.getAbility());
                a.getAbility().setTarget(pos);
                player.setNextAction(a);
                logger.info("We targeted {}", pos);
            });
        }
        if (game.isConfirmationRequired()) {
            drawConfirmationDialog();
            Optional<Boolean> confirmation = handleConfirmationInput();
            confirmation.ifPresent(choice -> {
                game.confirmationNoLongerRequired();
                if (Boolean.FALSE.equals(choice)) {
                    player.setNextAction(null);
                }
            });
        }

        game.getCurrentState().update();
        zone.getCurrentStage().updateTilesBasedOnFOV();
        logger.debug("Render calls: " + batch.renderCalls);
        logger.debug("Frames per second: " + Gdx.graphics.getFramesPerSecond());
    }

    private void drawInfoBox() {
        // We want to draw what is under the cursor.
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(infoX(), infoY(), Config.INFO_BOX_WIDTH, Config.INFO_BOX_HEIGHT);
        shapeRenderer.end();

        batch.begin();
        // Get the tile the cursor is on
        Position p = getStagePositionFromMousePosition(Position.get(Gdx.input.getX(), Gdx.input.getY()));
        // If there is an entity in the position, put the name in the box
        Entity e = zone.getCurrentStage().getEntityAtPosition(p);
        font.setColor(1.0f, 0.0f, 0.0f, 1.0f);

        if (e != null) {
            font.draw(batch, e.getName().name(), infoX(), infoY() + Config.INFO_BOX_HEIGHT);
            font.draw(batch, String.format("%s/%s", e.getHealth().getHp(), e.getHealth().getMaxHP()), infoX(), infoY() + Config.INFO_BOX_HEIGHT - 16);
        }
        // if there are items in the position, let the user know
        List<Item> items = zone.getCurrentStage().getItemsAtPosition(p);
        if (items.size() > 1) {
            font.draw(batch, "There are several items lying on the ground there.", infoX(), infoY() + Config.INFO_BOX_HEIGHT - 50);
        } else if (items.size() == 1) {
            font.draw(batch, String.format("You see a %s lying on the ground.", items.get(0).getItemName().getName()), infoX(), infoY() + Config.INFO_BOX_HEIGHT - 50);
        }

        // The terrain
        try {
            font.draw(batch, zone.getCurrentStage().getTileAt(p).getTerrain().toString(), infoX(), infoY() + Config.INFO_BOX_HEIGHT - 150);
        } catch (ArrayIndexOutOfBoundsException exception) {
            // TODO: Gracefully handle bounds for mouse support!
        }
        batch.end();
    }

    private void drawMessageLog() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Config.LOG_AREA_COLOR);
        shapeRenderer.rect(logX(), logY(), Config.LOG_AREA_WIDTH, Config.LOG_AREA_HEIGHT);
        shapeRenderer.end();

        List<Message> recentMessages = MessageLog.get().recentMessages();
        ListIterator<Message> iterator = recentMessages.listIterator();
        batch.begin();
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            Message m = iterator.next();
            for (MessageElement e : m.getElements()) {
                font.setColor(e.getColor());
                font.draw(batch, String.format("%s: %s", index, e.getText()), logX(), logY() + font.getLineHeight() * (index + 1));
            }
        }
        batch.end();
    }

    private void drawMouseHoverOverlay() {
        batch.begin();
        drawTileAtPosition(batch, Assets.textureMap.get(Assets.Glyphs.MOUSE_TARGET), getStagePositionFromMousePosition(Position.get(Gdx.input.getX(), Gdx.input.getY())));
        batch.end();
    }

    private void drawDebug() {
        batch.begin();
        font.setColor(Color.RED);
        font.draw(batch, String.format("FPS: %s", Gdx.graphics.getFramesPerSecond()), camera.position.x - 300, camera.position.y + 200);
        font.draw(batch, String.format("Position: {%s, %s}", zone.getCurrentStage().getPlayer().getPosition().getX(), zone.getCurrentStage().getPlayer().getPosition().getY()), camera.position.x - 300, camera.position.y + 250);
        font.draw(batch, String.format("Camera Position: %s", camera.position.toString()), camera.position.x - 300, camera.position.y + 150);
        font.draw(batch, String.format("Mouse Position: {%s, %s}", Gdx.input.getX(), Gdx.input.getY()), camera.position.x - 300, camera.position.y + 100);
        batch.end();
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
                    drawTileAtPosition(batch, tile.getTerrain().getTexture(), Position.get(x, y));
                }
            }
        }
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
                    drawTileAtPosition(batch, tile.getTerrain().getTexture(), Position.get(x, y));
                }
            }
        }
        batch.end();
    }

    private void drawItems() {
        batch.begin();

        this.zone.getCurrentStage().getItems().stream()
                .filter(item -> item.getPosition().isPresent())
                .forEach(item -> {
                    if (this.zone.getCurrentStage().getTileAt(item.getPosition().get()).isInFov()) {
                        drawTileAtPosition(batch, item.getTexture(), item.getPosition().get());
                    }
                });
        batch.end();
    }

    private void drawEntities() {
        batch.begin();

        for (Entity entity : this.zone.getCurrentStage().getEntities()) {
            if (this.zone.getCurrentStage().getTileAt(entity.getPosition()).isInFov()) {
                drawTileAtPosition(batch, entity.getTexture(), entity.getPosition());
            }
        }

        batch.end();
    }

    private void drawConfirmationDialog() {
        batch.begin();
        font.draw(batch, "Confirm action? (Y/N)", camera.position.x - 400, camera.position.y + 50);
        batch.end();
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

    /**
     * Calculate bottom left X coordinate of info block
     */
    private float infoX() {
        return camera.position.x + (Config.WINDOW_WIDTH / 2f - Config.SIDEBAR_WIDTH);
    }

    /**
     * Calculate bottom Y coordinate of info block
     *
     * @return
     */

    private float infoY() {
        return camera.position.y - (Config.WINDOW_HEIGHT / 2f);
    }

    private void drawPlayerStats() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Sidebar
        shapeRenderer.setColor(Config.SIDEBAR_COLOR);
        shapeRenderer.rect(sidebarX(), sidebarY(), Config.SIDEBAR_WIDTH, Config.SIDEBAR_HEIGHT);
        shapeRenderer.end();

        batch.begin();

        List<String> stats = new ArrayList<>();
        stats.add(player.getName().name());
        stats.add(String.format("Health: %s/%s", player.getHealth().getHp(), player.getHealth().getMaxHP()));
        stats.add(String.format("Strength: %s", player.getStatistics().getStrength().value()));
        stats.add(String.format("Intelligence: %s", player.getStatistics().getIntelligence().value()));
        stats.add(String.format("Dexterity: %s", player.getStatistics().getDexterity().value()));
        stats.add(String.format("Constitution: %s", player.getStatistics().getConstitution().value()));

        ListIterator<String> iterator = stats.listIterator();
        font.setColor(Color.WHITE);
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            font.draw(batch, iterator.next(), sidebarX() + 5, sidebarY() + Config.SIDEBAR_HEIGHT - (5 + 20 * index));
        }
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
        float maxWidth = (zone.getWidth() * Config.TILE_WIDTH) - (Config.WINDOW_WIDTH / 2.0f);
        float maxHeight = (zone.getHeight() * Config.TILE_HEIGHT) - (Config.WINDOW_HEIGHT / 2.0f);

        // Set it to player X * 24.0f, then clamp it?
        camera.position.x = MathUtils.clamp((position.getX() + 2) * Config.TILE_WIDTH, Config.WINDOW_WIDTH / 2.0f, maxWidth + Config.SIDEBAR_WIDTH);
        camera.position.y = MathUtils.clamp(position.getY() * Config.TILE_WIDTH, Config.WINDOW_HEIGHT / 2.0f - Config.LOG_AREA_HEIGHT, maxHeight);
        logger.trace(String.format("New camera position: %s", camera.position.toString()));
    }

    private Position getStagePositionFromMousePosition(Position pos) {
        // Calculate raw tile position
        // Calculate camera offset
        Position raw = Position.get(pos.getX(), ((-1 * pos.getY() + ((Config.WINDOW_HEIGHT - Config.LOG_AREA_HEIGHT) / 2))));

        Position offset = Position.get((int) (camera.position.x - Config.WINDOW_WIDTH / 2), (int) ((camera.position.y + (Config.LOG_AREA_HEIGHT / 2))));

        Position tilePosition = raw.add(offset);
        tilePosition.setX(tilePosition.getX() / Config.TILE_WIDTH);
        tilePosition.setY(tilePosition.getY() / Config.TILE_HEIGHT);

        return tilePosition;
    }

    private void drawTileAtPosition(Batch batch, TextureRegion region, Position position) {
        batch.draw(region, position.getX() * Config.TILE_WIDTH, position.getY() * Config.TILE_HEIGHT, Config.TILE_WIDTH, Config.TILE_HEIGHT);
    }

    public Optional<Position> handleTargetingInput() {
        Input input = Gdx.input;
        if (input.isKeyJustPressed(Input.Keys.ENTER) || input.isButtonPressed(Input.Buttons.LEFT)) {
            return Optional.of(getStagePositionFromMousePosition(Position.get(input.getX(), input.getY())));
        }
        return Optional.empty();
    }

    public Optional<Boolean> handleConfirmationInput() {
        Input input = Gdx.input;
        if (input.isKeyJustPressed(Input.Keys.Y) || input.isKeyJustPressed(Input.Keys.ENTER)) {
            return Optional.of(Boolean.TRUE);
        }
        if (input.isKeyJustPressed(Input.Keys.N) || input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            return Optional.of(Boolean.FALSE);
        }
        return Optional.empty();
    }
}