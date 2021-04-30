package com.jscisco.lom.domain.entity;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.Subject;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.attribute.Duration;
import com.jscisco.lom.domain.attribute.DurationEffect;
import com.jscisco.lom.domain.attribute.Effect;
import com.jscisco.lom.domain.attribute.InstantEffect;
import com.jscisco.lom.domain.attribute.Tag;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.LevelChangedEvent;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Entity implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    protected Name name;
    protected Level level;
    protected DijkstraMap dijkstraMap;
    protected Position position;
    protected FieldOfView fieldOfView = new FieldOfView(this);

    protected Map<Tag, Integer> tags = new HashMap<>();
    protected AttributeSet attributes = new AttributeSet();
    protected List<Effect> effects = new ArrayList<>();

    protected Inventory inventory = new Inventory();
    protected AssetDescriptor<Texture> asset;

    protected Action action = null;

    protected final Subject subject = new Subject();

    protected Entity() {
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Name name;
        protected Position position = Position.UNKNOWN;
        protected AssetDescriptor<Texture> asset;
        protected AttributeSet attributeSet;

        @SuppressWarnings("unchecked")
        public T withName(Name name) {
            this.name = name;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withPosition(Position position) {
            this.position = position;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withAsset(AssetDescriptor<Texture> asset) {
            this.asset = asset;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withAttributes(AttributeSet attributeSet) {
            this.attributeSet = attributeSet;
            return (T) this;
        }

        public abstract Entity build();

    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position) {
        this.position = position;
        this.calculateFieldOfView();
    }

    /**
     * This is called when an entity is added to a level.
     * It should:
     *  Calculate the initial DijkstraMap for the entity
     *  Calculate FOV *and* FOV Resistance Map for the level
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
        this.dijkstraMap = new DijkstraMap(getWeightsForDijkstraMap(), Measurement.EUCLIDEAN);
        recalculateDijkstraMap();
        this.calculateFieldOfView();
    }

    public Level getLevel() {
        return level;
    }

    public void draw(SpriteBatch batch, Assets assets) {
        Texture t = assets.getTexture(this.asset);
        batch.draw(t, position.getX() * t.getWidth(), position.getY() * t.getHeight());
    }

    public abstract Action nextAction();

    public void pickup(Item item) {
        inventory.addItem(item);
    }

    public void dropItem(Item item) {
        inventory.removeItem(item);
        level.getTileAt(this.position).addItem(item);
        subject.notify(null);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name=" + name +
                '}';
    }

    public void tick() {
        // Each turn, we should tick effects
        List<Effect> expiredEffects = new ArrayList<>();
        for (Effect effect : this.effects) {
            effect.tick();
            if (effect.isExpired()) {
                expiredEffects.add(effect);
            }
        }
        for (Effect effect : expiredEffects) {
            removeEffect(effect);
        }
    }

    public void applyEffect(Effect effect) {
        if (effect instanceof InstantEffect) {
            // Apply them immediately.
            effect.apply(this.attributes);
        }
        // Otherwise, it is an effect that makes changes over time. Thus, we add it to the entities active effects.
        // and toggle it on the appropriate modifiers
        // TODO: should effects be on the attribute set? Not necessarily.
        else {
            this.effects.add(effect);
            effect.apply(attributes);
        }
    }

    public void removeEffect(Effect effect) {
        // Here, we need to remove the modifiers that are on the attribute
        for (AttributeModifier modifier : effect.getModifiers()) {
            modifier.getAttribute().removeModifier(modifier);
        }
        // Then, we remove the effect
        this.effects.remove(effect);
    }

    public boolean hasTag(Tag tag) {
        return tags.containsKey(tag) && tags.get(tag) > 0;
    }

    public AttributeSet getAttributes() {
        return this.attributes;
    }

    public void onDied() {
        this.applyEffect(new DurationEffect()
                .withDuration(Duration.permanent())
                .grantTag(Tag.DEAD));
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Subject getSubject() {
        return subject;
    }

    public double[][] calculateFieldOfView() {
        return fieldOfView.calculateFOV();
    }

    public FieldOfView getFieldOfView() {
        return this.fieldOfView;
    }

    public AssetDescriptor<Texture> getAsset() {
        return asset;
    }

    @Override
    public void onNotify(Event event) {
        logger.info(MessageFormat.format("Entity notified about event {0}", event));
        if (event instanceof LevelChangedEvent) {
            recalculateDijkstraMap();
            this.fieldOfView.calculateFOV(true);
        }
    }

    private void recalculateDijkstraMap() {
        this.dijkstraMap.initialize(getWeightsForDijkstraMap());
    }

    private double[][] getWeightsForDijkstraMap() {
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                if (level.getTileAt(Position.of(x, y)).isWalkable(this)) {
                    weights[x][y] = DijkstraMap.FLOOR;
                } else {
                    weights[x][y] = DijkstraMap.WALL;
                }
            }
        }
        return weights;
    }

    public DijkstraMap getDijkstraMap() {
        return dijkstraMap;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
