package com.jscisco.lom.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * A Level is the representation of a single part of a Zone.
 * It contains everything that needs to be "known" for that particular space - all game objects and terrain.
 */
public class Level {

    // Reference to the player for convenience
    private GameObject player;
    /**
     * All game objects that are characters
     */
    private List<GameObject> entities = new ArrayList<>();

    /**
     * The discrete cells that make up a level.
     */
    private Cell[][] cells;

    private Level() {
    }

    public static class Builder {
        GameObject player;
        Cell[][] cells;

        public Builder() {
        }

        public Builder withPlayer(GameObject player) {
            this.player = player;
            return this;
        }

        public Builder withSize(int x, int y) {
            this.cells = new Cell[x][y];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    this.cells[i][j] = Cell.emptyFloor();
                }
            }
            return this;
        }

        public Level build() {
            if (this.cells == null) {
                throw new IllegalArgumentException("Size is required");
            }
            Level level = new Level();
            level.player = this.player;
            level.cells = this.cells;
            if (this.player != null) {
                level.entities.add(this.player);
                if (this.player.position != null) {
                    this.cells[this.player.position.x][this.player.position.y].entity = this.player;
                }
            }
            return level;
        }
    }

    public boolean addEntityAtPosition(GameObject entity, Position position) {
        if (!this.cells[position.x][position.y].isOccupied()) {
            this.cells[position.x][position.y].entity = entity;
            entity.position = position;
            entities.add(entity);
            return true;
        }
        return false;
    }

    public boolean moveEntity(GameObject entity, Position toPosition) {
        if (!this.cells[toPosition.x][toPosition.y].isOccupied()) {
            this.cells[entity.position.x][entity.position.y].entity = null;
            entity.position = toPosition;
            return true;
        }
        return false;
    }


}
