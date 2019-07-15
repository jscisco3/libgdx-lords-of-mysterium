package com.jscisco.lom.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jscisco.lom.action.DropItemAction;
import com.jscisco.lom.attributes.Equipment;
import com.jscisco.lom.attributes.Inventory;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InventoryScreen implements Screen {

    private Logger logger = LoggerFactory.getLogger(InventoryScreen.class);

    private Zone zone;
    private Game game;
    private Player player;
    private Inventory inventory;
    private Equipment equipment;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;
    private BitmapFont font;
    private static final GlyphLayout layout = new GlyphLayout();

    private boolean inventoryActive = true;
    private int selectedItemIndex = 0;
    private int selectedEquipmentIndex = 0;

    public InventoryScreen(Game game, Zone zone) {
        this.game = game;
        this.zone = zone;
        this.player = zone.getCurrentStage().getPlayer();
        this.inventory = this.player.getInventory();
        this.equipment = this.player.getEquipment();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        font = Config.createFont(32);
        font.setColor(1f, 1f, 0, 1);
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

        renderInventory();
        renderEquipment();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            this.game.setScreen(new ZoneScreen(this.game, this.zone));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Go ahead and drop the item here, because it should be free to do!
            if (inventoryActive) {
                this.player.setNextAction(new DropItemAction(this.player, this.inventory.getItems().get(selectedItemIndex)));
                this.player.getNextAction().invoke();
                decrementSelectedItem();
            } else {
                // Unequip selected item;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            if (!this.inventory.getItems().isEmpty()) {
                Item itemToEquip = this.inventory.getItems().get(selectedItemIndex);
                this.inventory.removeItem(itemToEquip);
                List<Item> itemsUnequipped = this.player.getEquipment().equip(itemToEquip);
                for (Item item : itemsUnequipped) {
                    this.inventory.addItem(item);
                }
                decrementSelectedItem();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.inventoryActive = !this.inventoryActive;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (this.inventoryActive && !this.inventory.getItems().isEmpty()) {
                incrementSelectedItem();
            }
            if (!this.inventoryActive) {
                incrementSelectedEquipment();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (this.inventoryActive && !this.inventory.getItems().isEmpty()) {
                decrementSelectedItem();
            }
            if (!this.inventoryActive) {
                decrementSelectedEquipment();
            }

        }
    }

    private String getNameOfEquipment(int index) {
        List<Item> equippedItems = this.equipment.getSlots();
        StringBuilder sb = new StringBuilder();
        sb.append(this.equipment.getSlotTypes().get(index));
        sb.append(" - ");
        sb.append(equippedItems.get(index) != null ? equippedItems.get(index).getItemType().getName() : "Nothing");
        return sb.toString();
    }

    private void renderEquipment() {
        float spacing = 10f;
        // Selected Item Highlight
        layout.setText(font, getNameOfEquipment(selectedEquipmentIndex));
        if (!this.inventoryActive) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Config.SELECTED_ITEM_COLOR);
            shapeRenderer.rect(camera.position.x - Config.WINDOW_WIDTH / 2 + spacing / 2, camera.position.y - ((selectedEquipmentIndex + 1) * (layout.height + spacing)) + (Config.WINDOW_HEIGHT / 2), layout.width + spacing, layout.height + spacing);
            shapeRenderer.end();
        }

        batch.begin();
        List<Equipment.EquipmentSlot> equipmentSlots = this.equipment.getSlotTypes();
        List<Item> equippedItems = this.equipment.getSlots();
        for (int i = 0; i < equipmentSlots.size(); i++) {
            font.draw(batch, getNameOfEquipment(i), camera.position.x - Config.WINDOW_WIDTH / 2 + spacing, camera.position.y + Config.WINDOW_HEIGHT / 2 - (i * (layout.height + spacing)));
        }

        batch.end();
    }

    private void renderInventory() {

        float spacing = 10f;

        batch.begin();
        if (this.inventory.getItems().isEmpty()) {
            layout.setText(font, "You have no items");
            font.draw(batch, "You have no items.", camera.position.x, camera.position.y);
        }
        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            layout.setText(font, this.inventory.getItems().get(i).toString());
            font.draw(batch, this.inventory.getItems().get(i).toString(), camera.position.x, camera.position.y - (i * (layout.height + spacing)));
        }
        batch.end();

        if (this.inventoryActive) {
            if (!this.inventory.getItems().isEmpty()) {
                // Selected Item Highlight
                layout.setText(font, this.inventory.getItems().get(selectedItemIndex).toString());
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Config.SELECTED_ITEM_COLOR);
                shapeRenderer.rect(camera.position.x - spacing / 2, camera.position.y - ((selectedItemIndex + 1) * (layout.height + spacing)), layout.width + spacing, layout.height + spacing);
                shapeRenderer.end();
            }
        }


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

    private void incrementSelectedEquipment() {
        this.selectedEquipmentIndex = (this.selectedEquipmentIndex + 1) % this.equipment.getSlots().size();
    }

    private void decrementSelectedEquipment() {
        this.selectedEquipmentIndex -= 1;
        logger.debug("Selected Equipment Index: {}", this.selectedEquipmentIndex);
        if (this.selectedEquipmentIndex < 0) {
            this.selectedEquipmentIndex = this.equipment.getSlotTypes().size() - 1;
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
