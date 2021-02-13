package com.jscisco.lom.domain;


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

    public abstract Action getNextCommand();

}
