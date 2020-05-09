package com.jscisco.lom.shelf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents the current, in memory representation of the location in game.
 * Example: The floor of the dungeon that is currently being explored.
 * It contains the items, entities, tiles, etc. that make up what is being explored.
 */
public class Stage implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(Stage.class);

    Tile[][] tiles;
    Size size;
    List<Entity> entities = new ArrayList<>();

    public Stage(Size size) {
        this.size = size;
    }

    /**
     * Add an entity to the stage, and update that entities reference.
     *
     * @param e The entity being added
     */
    void addEntity(Entity e) {
        this.entities.add(e);
        e.stage = this;
    }

    @Override
    public void update(Event event) {
        if (event instanceof EntityDiedEvent) {
            entities.remove(((EntityDiedEvent) event).entity());
        }
    }
}
