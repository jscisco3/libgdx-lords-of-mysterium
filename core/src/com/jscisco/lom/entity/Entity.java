package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.attributes.*;
import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import squidpony.squidai.DijkstraMap;

import java.util.List;

import static com.jscisco.lom.combat.DamageType.BLUNT;

public abstract class Entity {

    protected String name;
    protected FieldOfView fieldOfView;
    protected Position position;
    protected Health health;
    protected Energy energy;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Stats stats;
    protected Job job;
    protected Assets.Glyphs glyph;

    protected transient Stage stage;
    protected transient Action nextAction;
    protected transient DijkstraMap pathingMap;

    @Inject
    protected EventBus eventBus;

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

    public Stats getStats() {
        return stats;
    }

    public String getName() {
        return name;
    }

    public Job getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Attack getAttack() {
        // First, try to get any attack from the equipped weapons
        if (this.equipment == null) {
            return unarmedAttack();
        }
        List<Item> weapons = this.equipment.getWeapons();
        if (weapons.isEmpty()) {
            // Default unarmed attack
            return unarmedAttack();
        }
        // Otherwise, return the first attack and we will handle multiple weapons later.
        return weapons.get(0).getAttack();
    }

    private Attack unarmedAttack() {
        return new Attack(0, new Damage(BLUNT, 5));
    }

}
