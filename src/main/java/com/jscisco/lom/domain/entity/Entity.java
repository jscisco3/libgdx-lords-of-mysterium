package com.jscisco.lom.domain.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.*;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.map.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;

import java.util.Objects;
import java.util.UUID;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Entity implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    protected UUID id = UUID.randomUUID();

    protected Name name;

    @JsonIgnore
    protected Level level;

    @JsonIgnore
    protected DijkstraMap dijkstraMap;

    protected Position position;
    @JsonIgnore
    protected FieldOfView fieldOfView = new FieldOfView(this);

    protected String glyph = "ring";

    protected Inventory inventory;

    protected Pools pools;

    @JsonIgnore
    protected Action action = null;

    @JsonIgnore
    protected final Subject subject = new Subject();

    protected Entity() {
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Name name;
        protected Position position = Position.UNKNOWN;
        protected String glyph;
        protected Pools pools;

        @SuppressWarnings("unchecked")

        public T withPools(Pools pools) {
            this.pools = pools;
            return (T) this;
        }

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
        public T withGlyph(String asset) {
            this.glyph = asset;
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
     * This is called when an entity is added to a level. It should: Calculate the initial DijkstraMap for the entity
     * Calculate FOV *and* FOV Resistance Map for the level
     * <p>
     * TODO: Is this necessary?
     *
     * @param level
     */
    public void setLevel(Level level) {
        if (level == null) {
            logger.info("Setting level to null");
            this.level = null;
            return;
        }
        this.level = level;
        this.dijkstraMap = new DijkstraMap(getWeightsForDijkstraMap(), Measurement.EUCLIDEAN);
        recalculateDijkstraMap();
        this.calculateFieldOfView();
    }

    public Level getLevel() {
        return level;
    }

    public void draw(SpriteBatch batch, Assets assets) {
        Sprite s = new Sprite(assets.getTextureRegion(this.glyph));
        s.setPosition(s.getWidth() * position.getX(), s.getHeight() * position.getY());
        s.draw(batch);
    }

    public abstract Action nextAction();

    public void pickup(Item item) {
        inventory.addItem(item);
        // level.removeItem(item);
    }

    public void dropItem(Item item) {
        inventory.removeItem(item);
        // level.addItemAtPosition(item, position);
        subject.notify(null);
    }

    public void tick() {
    }

    public void onDied() {
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Subject getSubject() {
        return subject;
    }

    public double[][] calculateFieldOfView() {
        return null;
    }

    public FieldOfView getFieldOfView() {
        return this.fieldOfView;
    }

    public String getGlyph() {
        return glyph;
    }

    public Pools getPools() {
        return pools;
    }

    @Override
    public void onNotify(Event event) {
    }

    private void recalculateDijkstraMap() {
        this.dijkstraMap.initialize(getWeightsForDijkstraMap());
    }

    private double[][] getWeightsForDijkstraMap() {
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                if (level.getTile(Position.of(x, y)).isWalkable(this)) {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", name=" + name + ", position=" + position + '}';
    }
}
