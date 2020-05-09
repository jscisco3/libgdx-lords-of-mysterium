package com.jscisco.lom.shelf.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.shelf.LOMGame_Deprecated;
import com.jscisco.lom.shelf.ability.Ability;
import com.jscisco.lom.shelf.action.Action;
import com.jscisco.lom.shelf.assets.Assets;
import com.jscisco.lom.shelf.combat.Attack;
import com.jscisco.lom.shelf.combat.Defense;
import com.jscisco.lom.shelf.domain.Health;
import com.jscisco.lom.shelf.effect.Effect;
import com.jscisco.lom.shelf.effect.TimedEffect;
import com.jscisco.lom.shelf.items.EquipmentSlot;
import com.jscisco.lom.shelf.items.Item;
import com.jscisco.lom.shelf.items.ItemCannotBeEquippedException;
import com.jscisco.lom.shelf.items.Slot;
import com.jscisco.lom.shelf.terrain.Terrain;
import com.jscisco.lom.shelf.domain.Position;
import com.jscisco.lom.shelf.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Deprecated
public abstract class Entity {

    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    protected EntityName name;
    protected FieldOfView fieldOfView;
    protected Position position;
    protected Health health;
    protected Energy energy;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Statistics statistics;
    protected List<Effect> effects = new ArrayList<>();
    protected Job job;
    protected Assets.Glyphs glyph;
    protected List<Attack> unarmedAttacks;
    protected Defense defense;

    protected List<Ability> knownAbilities = new ArrayList<>();

    protected DeathStrategy deathStrategy;

    protected transient Stage stage;
    protected transient Action nextAction;
    protected transient DijkstraMap pathingMap;

    public Entity(Entity other) {
        this.name = other.name;
        this.fieldOfView = other.fieldOfView;
        this.position = other.position;
        this.health = other.health;
        this.energy = other.energy;
        this.inventory = other.inventory;
        this.equipment = other.equipment;
        this.statistics = other.statistics;
        this.effects = new ArrayList<>(other.effects);
        this.job = other.job;
        this.glyph = other.glyph;
        this.knownAbilities = new ArrayList<>(other.knownAbilities);
        this.unarmedAttacks = new ArrayList<>(other.unarmedAttacks);
        this.defense = other.defense;

        this.stage = other.stage;
        this.nextAction = other.nextAction;
        this.pathingMap = other.pathingMap;

    }

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
        return position.x();
    }

    public int getY() {
        return position.y();
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
        return unarmedAttacks;
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

    public Item unequip(EquipmentSlot slot) {
        return slot.unequip();
    }

    public void die() {
        this.deathStrategy.die(this);
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void applyEffect(Effect effect) {
        effect.apply(this);
    }

    public void removeEffect(TimedEffect effect) {
        this.effects.remove(effect);
    }

    public List<Ability> getKnownAbilities() {
        return knownAbilities;
    }

    public void learnAbility(Ability ability) {
        this.knownAbilities.add(ability);
    }

    public void setDefense(Defense defense) {
        this.defense = defense;
    }

    public Defense getDefense() {
        return this.defense;
    }

    public void attack(Entity defender) {
        List<Attack> attacks = this.getAttacks();
        Defense defense = defender.getDefense();
        // Modify attack & defense based on Attackers OnAttack triggers and Defenders OnDefend triggers
        // Determine if hit via random(accuracy) >= defense.ev
        attacks.forEach(a -> {
            if (LOMGame_Deprecated.rng.nextInt(a.getAccuracy()) > defense.getEv().getEv()) {
                a.getDamages().forEach(d -> {
                    int damageDealt = Math.max(d.getDamage(), defense.getAc().getAc());
                    logger.info("Attacker: {} dealt {} damage to Defender: {}", this, damageDealt, defender);
                    defender.health.reduce(Math.max(d.getDamage(), defense.getAc().getAc()));
                });
            }
        });
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name=" + name +
                '}';
    }
}
