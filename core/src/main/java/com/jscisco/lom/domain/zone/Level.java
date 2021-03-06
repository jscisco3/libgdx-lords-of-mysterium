package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.Subject;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.domain.event.level.LevelEvent;
import com.jscisco.lom.domain.item.Item;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

// TODO: Saving and loading a game should put the hero directly into the level.
// TODO: We should load the _entire_ zone into memory, including all levels, and go from there.
@javax.persistence.Entity
public class Level {

    private static final Logger logger = LoggerFactory.getLogger(Level.class);

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    // Orphan Removal == true so that when an entity is removed from this list, it is deleted from the DB.
    // However, that could be a problem for the hero. So, perhaps, we need the GameScreen to observe the level.
    // And when we remove an entity, we publish that fact. The GameScreen then uses EntityService.deleteEntity(entityId)
    // Fetch eager because we just want everything loaded in memory. Revisit if it becomes a problem.
    // TODO: Does orphan removal still make sense?
    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Entity> entities = new ArrayList<>();

    private int currentActorIndex;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @Transient
    private Tile[][] tiles;

    private int width;
    private int height;

    // TODO: Consider depth as a property of level to control drops, monsters, etc. Also sorting when retrieving a zone
    @Transient
    private int depth;

    @Transient
    private final Subject subject = new Subject();

    public Level() {
    }

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Level(int width, int height, LevelGeneratorStrategy generator) {
        this.width = width;
        this.height = height;
        this.currentActorIndex = 0;
        tiles = generator.generate(this.width, this.height);
    }

    /**
     * This method processes all actors, returning when we have a null action (this indicates we are waiting for the player
     * to input a command). If we have a null action, and the current actor is an NPC - we will log a warning and skip that actor.
     * <p>
     * However, this does not work if we have a state for the player that returns an action at all times.
     */
    public void processAllActors() {
        while (true) {
            Entity currentEntity = entities.get(currentActorIndex);
            Action action = currentEntity.nextAction();
            if (action == null) {
                if (currentEntity instanceof NPC) {
                    logger.error("NPC with a null action: " + String.valueOf(currentEntity));
                }
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
    }

    // TODO: Consider if we should have something else (e.g. EntityProcessor) handle this?

    /**
     * Process actions from the actors in the current stage. Currently processes a single actor.
     */
    public void process() {
        Action action = entities.get(currentActorIndex).nextAction();
        if (action != null) {
            logger.trace("Current actor index: " + currentActorIndex);
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
        // TODO: Clean this up to make it nicer
        if (entities.isEmpty()) {
            return;
        }
        currentActorIndex = (currentActorIndex + 1) % entities.size();
        // Here, we can start the next actors turn
        entities.get(currentActorIndex).tick();
    }

    public Tile getTileAt(Position position) {
        return tiles[position.getX()][position.getY()];
    }

    public void addEntityAtPosition(Entity entity, Position position) {
        logger.info(MessageFormat.format("Adding entity: {0} and position: {1}", entity.getName().getName(), position.toString()));
        entity.setPosition(position);
        entity.setLevel(this);
        logger.info(String.valueOf(entity.getLevel().id));
        this.entities.add(entity);
        this.subject.register(entity);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void removeEntity(Entity entity) {
        // Have to remove it from the tile as well...
        this.entities.remove(entity);
        entity.setLevel(null);
    }

    /**
     * Returns the first tile that is walkable for the entity and unoccupied
     *
     * @param e
     * @return
     */
    public Position getEmptyTile(Entity e) {
        Set<Position> occupiedPositions = entities.stream().map(Entity::getPosition).collect(Collectors.toSet());
        List<Position> walkable = walkablePositions(e);
        walkable.removeAll(occupiedPositions);
        return walkable.get(GameConfiguration.random.nextInt(walkable.size()));
    }

    // TODO: Maybe make this getUnexploredWalkablePositions(Entity e)
    public List<Position> getUnexploredPositions() {
        List<Position> unexplored = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!getTileAt(Position.of(i, j)).isExplored() && getTileAt(Position.of(i, j)).feature instanceof Floor) {
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
        this.items.add(item);
        item.setPosition(position);
        item.setLevel(this);
    }

    public List<Item> getItemsAtPosition(Position p) {
        return items.stream().filter(i -> {
            assert i.getPosition() != null;
            return i.getPosition().equals(p);
        }).collect(Collectors.toList());
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        item.setLevel(null);
    }

    public void setTile(Tile t, Position p) {
        tiles[p.getX()][p.getY()] = t;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
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

    public Optional<Entity> getEntityAtPosition(Position p) {
        return entities.stream().filter(e -> e.getPosition().equals(p)).findFirst();
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Hero getHero() {
        return (Hero) entities.stream().filter(e -> e instanceof Hero).findFirst().get();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCurrentActorIndex() {
        return currentActorIndex;
    }

    /**
     * Used for regenerating state of a level
     */
    public void processEvents(List<LevelEvent> events) {
//        for (LevelEvent event : events) {
//            logger.info("Event: " + event);
//        }
        logger.info("Processing " + events.size() + " events.");
        for (LevelEvent event : events) {
            logger.trace("Processing event: " + event);
            event.process(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Level level = (Level) o;
        return id.equals(level.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Level{" +
                "id=" + id +
                '}';
    }
}
