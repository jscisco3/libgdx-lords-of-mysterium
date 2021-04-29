package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.ActionResult;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private static final Logger logger = LoggerFactory.getLogger(Level.class);

    private LevelGeneratorStrategy generator;

    private Hero hero;
    private List<Entity> entities = new ArrayList<>();
    private int currentActorIndex = 0;

    private Tile[][] tiles;

    private final int width;
    private final int height;

    public Level() {
        this(80, 40, new LevelGeneratorStrategy.EmptyLevelStrategy());
        getTileAt(Position.of(5, 5)).setFeature(FeatureFactory.WALL);
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

    public void addEntityAtPosition(Entity entity, Position position) {
        this.entities.add(entity);
        this.getTileAt(position).occupy(entity);
        entity.setLevel(this);
        entity.move(position);
        entity.calculateFieldOfView();
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

    public void addHero(Hero hero) {
        this.hero = hero;
        addEntityAtPosition(hero, getEmptyTile(hero));
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
}
