package com.jscisco.lom.domain.actor;


import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.Position;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Actor {

    protected ActorName name;
    protected GameplayAttributes attributes;
    protected Position position;

    protected Action action = null;

    protected Actor() {
    }

    public static abstract class Builder {
        protected ActorName name;
        protected GameplayAttributes attributes;
        protected Position position = Position.UNKNOWN;

        public Builder withName(ActorName name) {
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

        public abstract Actor build();

    }

    public ActorName getName() {
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

    public abstract Action nextAction();

}
