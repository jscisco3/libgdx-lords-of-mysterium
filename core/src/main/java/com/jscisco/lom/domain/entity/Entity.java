package com.jscisco.lom.domain.entity;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.Observer;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.Subject;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shelf.attribute.AttributeSet;
import shelf.attribute.Duration;
import shelf.attribute.DurationEffect;
import shelf.attribute.Effect;
import shelf.attribute.Tag;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Entity implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    @Id
    protected UUID id = UUID.randomUUID();

    @Embedded
    protected Name name;

    @ManyToOne
    @JoinColumn(name = "level_id")
    protected Level level;

    @Transient
    protected DijkstraMap dijkstraMap;
    @Embedded
    protected Position position;
    @Transient
    protected FieldOfView fieldOfView = new FieldOfView(this);

    protected String glyph = "ring";

    @OneToOne(mappedBy = "entity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    protected Inventory inventory;

    @Transient
    protected Action action = null;

    @Transient
    protected final Subject subject = new Subject();

    protected Entity() {
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Name name;
        protected Position position = Position.UNKNOWN;
        protected String glyph;
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
        public T withGlyph(String asset) {
            this.glyph = asset;
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
     * Calculate the initial DijkstraMap for the entity
     * Calculate FOV *and* FOV Resistance Map for the level
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
        level.removeItem(item);
    }

    public void dropItem(Item item) {
        inventory.removeItem(item);
        level.addItemAtPosition(item, position);
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

    @Override
    public void onNotify(Event event) {
        logger.info(MessageFormat.format("Entity notified about event {0}", event));
        if (event instanceof LevelEvent) {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        inventory.setEntity(this);
    }

    public void setGlyph(String glyph) {
        this.glyph = glyph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name=" + name +
                ", position=" + position +
                '}';
    }
}
