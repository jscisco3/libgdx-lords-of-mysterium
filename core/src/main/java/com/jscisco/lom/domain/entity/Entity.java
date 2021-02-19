package com.jscisco.lom.domain.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.zone.Level;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Entity {

    protected EntityName name;
    protected Level level;
    protected AttributeSet attributes;
    protected Position position;
    protected Inventory inventory;
    protected Texture texture;

    protected Action action = null;

    protected Entity() {
    }

    public static abstract class Builder {
        protected EntityName name;
        protected AttributeSet attributes;
        protected Position position = Position.UNKNOWN;
        protected Texture texture;

        public Builder withName(EntityName name) {
            this.name = name;
            return this;
        }

        public Builder withAttributes(AttributeSet attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withTexture(Texture texture) {
            this.texture = texture;
            return this;
        }

        public abstract Entity build();

    }

    public EntityName getName() {
        return name;
    }

    public AttributeSet getAttributes() {
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

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, position.getX() * this.texture.getWidth(), position.getY() * this.texture.getHeight());
    }

    public abstract Action nextAction();

}
