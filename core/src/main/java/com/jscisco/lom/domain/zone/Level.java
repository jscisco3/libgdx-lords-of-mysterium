package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.Subject;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
public class Level {

    private static final Logger logger = LoggerFactory.getLogger(Level.class);

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @Transient
    private LevelGeneratorStrategy generator;

    @OneToOne(mappedBy = "level", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Hero hero;

    @Transient
    private List<Entity> entities = new ArrayList<>();

    @Transient
    private int currentActorIndex = 0;

    @Transient
    private Tile[][] tiles;

    @Transient
    private final int width;
    @Transient
    private final int height;

    @Transient
    private final Subject subject = new Subject();

    public Level() {
        this(80, 40, new LevelGeneratorStrategy.EmptyLevelStrategy());
    }

    public Level(int width, int height, LevelGeneratorStrategy generator) {
        this.width = width;
        this.height = height;
        this.generator = generator;
        tiles = generator.generate(this.width, this.height);

//        this.addEntityAtPosition(EntityFactory.golem(), Position.of(5, 5));
//        addItemAtPosition(ItemFactory.sword(), Position.of(5, 5));
//        addItemAtPosition(ItemFactory.sword(), Position.of(1, 1));
//        addItemAtPosition(ItemFactory.ring(), Position.of(1, 1));
//        addItemAtPosition(ItemFactory.ring(), Position.of(1, 1));
//        addItemAtPosition(ItemFactory.sword(), Position.of(1, 1));
    }

    // TODO: Consider if we should have something else (e.g. EntityProcessor) handle this?

    /**
     * Process actions from the actors in the current stage
     */
    public void process() {
        Action action = entities.get(currentActorIndex).nextAction();
        if (action != null) {
            logger.trace(action.toString());
        }
        // No action, so skip
        if (action == null) {
            return;
        }
        while (true) {
            ActionResult result = action.execute();
            if (!result.success()) {
                // Action failed, so don't increment active actor
                return;
            }
            if (!result.hasAlternate()) {
                // No alternative and the action has succeeded, so continue on.
                break;
            }
            // We have an alternative, so we must process that one before we know if we have ultimately succeeded
            action = result.getAlternative();
        }
        currentActorIndex = (currentActorIndex + 1) % entities.size();
        // Here, we can start the next actors turn
        entities.get(currentActorIndex).tick();
    }

    public Tile getTileAt(Position position) {
        return tiles[position.getX()][position.getY()];
    }

    /**
     * Adds an entity to the map at the given position. As of 4/30/2021, there are no checks to see if it
     * is a walkable tile
     *
     * @param entity
     * @param position
     */
    public void addEntityAtPosition(Entity entity, Position position) {
        logger.info(MessageFormat.format("Adding entity: {0} and position: {1}", entity.getName().getName(), position.toString()));
        entity.setPosition(position);
        if (entity instanceof Hero) {
            setHero((Hero) entity);
        }
        this.entities.add(entity);
        entity.setLevel(this);
        this.subject.register(entity);
        this.getTileAt(position).occupy(entity);
    }

    /**
     * Responsible for rendering the level to the given SpriteBatch from the hero's perspective
     *
     * @param batch
     * @param assets
     * @param camera
     */
    public void draw(SpriteBatch batch, Assets assets, Camera camera) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = getTileAt(Position.of(i, j));
                if (this.hero.getFieldOfView().isInSight(Position.of(i, j))) {
                    t.draw(batch, assets, i, j, true);
                } else if (t.isExplored()) {
                    batch.setColor(Color.DARK_GRAY);
                    t.draw(batch, assets, i, j, false);
                    batch.setColor(Color.WHITE);
                }
//                else if (tiles.get(i).get(j).isExplored() && !this.hero.getFieldOfView().isInSight(Position.of(i, j))) {
//                    batch.setColor(Color.GRAY);
//                    tiles.get(i).get(j).draw(batch, assets, i, j, false);
//                    batch.setColor(Color.WHITE);
//                }
            }
        }
        batch.end();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void removeEntity(Entity entity) {
        // Have to remove it from the tile as well...
        this.getTileAt(entity.getPosition()).removeOccupant();
        this.entities.remove(entity);
    }

    /**
     * Returns the first tile that is walkable for the entity and unoccupied
     *
     * @param e
     * @return
     */
    public Position getEmptyTile(Entity e) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile t = tiles[i][j];
                if (!t.isOccupied() && t.isWalkable(e)) {
                    return Position.of(i, j);
                }
            }
        }
        throw new RuntimeException("Could not find empty tile for entity: " + e);
    }

    public List<Position> getUnexploredPositions() {
        List<Position> unexplored = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!getTileAt(Position.of(i, j)).isExplored()) {
                    unexplored.add(Position.of(i, j));
                }
            }
        }
        return unexplored;
    }

    public Tile getTileOccupiedByEntity(Entity entity) {
        return getTileAt(entity.getPosition());
    }

    public void addItemAtPosition(Item item, Position position) {
        getTileAt(position).addItem(item);
    }

    public void setTile(Tile t, Position p) {
        tiles[p.getX()][p.getY()] = t;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public List<Position> walkablePositions(Entity e) {
        List<Position> walkable = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (tiles[i][j].isWalkable(e)) {
                    walkable.add(Position.of(i, j));
                }
            }
        }
        return walkable;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Hero getHero() {
        return hero;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
        hero.setLevel(this);
    }
}
