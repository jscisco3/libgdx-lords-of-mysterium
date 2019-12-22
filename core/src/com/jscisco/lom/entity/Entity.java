package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.UnarmedAttackFactory;
import com.jscisco.lom.items.EquipmentSlot;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemCannotBeEquippedException;
import com.jscisco.lom.items.Slot;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import squidpony.squidai.DijkstraMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Entity {

    protected EntityName name;
    protected FieldOfView fieldOfView;
    protected Position position;
    protected Health health;
    protected Energy energy;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Statistics statistics;
    protected Job job;
    protected Assets.Glyphs glyph;
    protected DeathStrategy deathStrategy;

    protected transient Stage stage;
    protected transient Action nextAction;
    protected transient DijkstraMap pathingMap;

    protected Entity() {
        this.pathingMap = new DijkstraMap();
        this.updatePathingMap();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(FieldOfView fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public TextureRegion getTexture() {
        if (this.job != null) {
            return this.job.getIcon();
        }
        return Assets.textureMap.get(glyph);
    }

    public Assets.Glyphs getGlyph() {
        return glyph;
    }

    public void setGlyph(Assets.Glyphs glyph) {
        this.glyph = glyph;
    }

    public Action getNextAction() {
        Action action = nextAction;
        // Only do this action once!
        nextAction = null;
        return action;
    }

    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }

    public DijkstraMap getPathingMap() {
        return pathingMap;
    }

    public void updatePathingMap() {
        if (stage != null) {
            double[][] costs = new double[stage.getWidth()][stage.getHeight()];
            for (int x = 0; x < stage.getWidth(); x++) {
                for (int y = 0; y < stage.getHeight(); y++) {
                    Terrain t = stage.getTerrainAtPosition(x, y);
                    if (t.isWalkable()) {
                        costs[x][y] = DijkstraMap.FLOOR;
                    } else {
                        costs[x][y] = DijkstraMap.WALL;
                    }
                }
            }
            this.pathingMap.initialize(costs);
        }
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public EntityName getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public void setName(EntityName name) {
        this.name = name;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Attack> getAttacks() {
        if (equipment != null) {
            List<Item> weapons = equipment.getWeapons();

        }
        return UnarmedAttackFactory.getUnarmedAttacks();
    }

    public boolean drop(Item item) {
        // Can only drop items in your inventory
        if (this.inventory.removeItem(item)) {
            item.setPosition(this.position);
            this.getStage().addItem(item);
            return true;
        }
        return false;
    }

    public List<Item> equip(Item item) throws ItemCannotBeEquippedException {
        if (Objects.isNull(equipment)) {
            throw new IllegalStateException("Entity cannot equip if they don't have an equipment component");
        }
        if (!item.equippable()) {
            throw new ItemCannotBeEquippedException();
        }
        Slot slot = item.getSlot().orElseThrow(ItemCannotBeEquippedException::new);
        List<Item> unequipped = new ArrayList<>();
        List<EquipmentSlot> validSlots = this.equipment.getSlotsByType(slot);
        for (EquipmentSlot es : validSlots) {
            // We found a valid slot that is empty, so add this item to it
            // and return an empty list of unequipped items.
            if (!es.hasItem()) {
                es.equip(item);
                return unequipped;
            }
        }
        // We have to replace something. So we'll use the first
        validSlots.get(0).equip(item).ifPresent(unequipped::add);
        return unequipped;
    }

    public void die() {
        this.deathStrategy.die(this);
    }

}
