package com.jscisco.lom.domain.entity;


import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.Level;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Entity {

    protected EntityName name;
    protected Level level;
    protected GameplayAttributes attributes;
    protected Position position;
    protected Inventory inventory;

    protected Action action = null;

    protected Entity() {
    }

    public static abstract class Builder {
        protected EntityName name;
        protected GameplayAttributes attributes;
        protected Position position = Position.UNKNOWN;

        public Builder withName(EntityName name) {
            this.name = name;
            return this;
        }

        public Builder withAttributes(GameplayAttributes attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public abstract Entity build();

    }

    public EntityName getName() {
        return name;
    }

    public GameplayAttributes getAttributes() {
        return attributes;
    }

    public Position getPosition() {
        return position;
    }

    public void move(Position position) {
        this.position = position;
    }

    public void setStage(Level level) {
        this.level = level;
    }

    public Level getStage() {
        return level;
    }

    public abstract Action nextAction();

}
