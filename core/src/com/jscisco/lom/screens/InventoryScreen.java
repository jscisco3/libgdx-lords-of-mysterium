package com.jscisco.lom.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jscisco.lom.action.DropItemAction;
import com.jscisco.lom.attributes.Inventory;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(InventoryScreen.class);

    private Zone zone;
    private Game game;
    private Player player;
    private Inventory inventory;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private BitmapFont font;

    private int selectedItemIndex = 0;

    public InventoryScreen(Game game, Zone zone) {
        this.game = game;
        this.zone = zone;
        this.player = zone.getCurrentStage().getPlayer();
        this.inventory = this.player.getInventory();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        font = createFont();
        font.setColor(1f, 1f, 0, 1);
    }

    private BitmapFont createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("../../fonts/consola.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (24 * Gdx.graphics.getDensity());
        return generator.generateFont(parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);

        if (!this.inventory.getItems().isEmpty()) {

            // Selected Item Highlight
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Config.SELECTED_ITEM_COLOR);
            shapeRenderer.rect(camera.position.x - Config.WINDOW_WIDTH / 2 + (24f * 2), camera.position.y - ((selectedItemIndex + 1) * 24f) + (Config.WINDOW_HEIGHT / 2), this.inventory.getItems().get(selectedItemIndex).toString().length() * 10f, 24f);
//            shapeRenderer.rect(camera.position.x, camera.position.y, this.items.get(selectedItemIndex).toString().length() * 10f, 24f);
            shapeRenderer.end();
        }


        batch.begin();

        if (this.inventory.getItems().isEmpty()) {
            font.draw(batch, "You have no items.", camera.position.x, camera.position.y);
        }

        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            font.draw(batch, this.inventory.getItems().get(i).toString(), camera.position.x - Config.WINDOW_WIDTH / 2 + (24f * 3), camera.position.y + Config.WINDOW_HEIGHT / 2 - (i * 24f));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.setScreen(new ZoneScreen(this.game, this.zone));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Go ahead and drop the item here, because it should be free to do!
            this.player.setNextAction(new DropItemAction(this.player, this.inventory.getItems().get(selectedItemIndex)));
            this.player.getNextAction().invoke();
            decrementSelectedItem();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            incrementSelectedItem();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            decrementSelectedItem();
        }

        batch.end();
    }

    private void incrementSelectedItem() {
        this.selectedItemIndex = (this.selectedItemIndex + 1) % this.inventory.getItems().size();
    }

    private void decrementSelectedItem() {
        this.selectedItemIndex -= 1;
        if (this.selectedItemIndex < 0) {
            this.selectedItemIndex = this.inventory.getItems().size() - 1;
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

    }

    @Override
    public void dispose() {

    }
}
