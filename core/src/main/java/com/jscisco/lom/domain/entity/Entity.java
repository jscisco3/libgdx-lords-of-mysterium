package com.jscisco.lom.domain.entity;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.attribute.*;
import com.jscisco.lom.domain.zone.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representation of any character in the game (e.g. NPCs, Player)
 */
public abstract class Entity {

    protected EntityName name;
    protected Level level;
    protected Position position;

    protected Map<Tag, Integer> tags = new HashMap<>();
    protected Map<Attribute.AttributeType, Attribute> attributes = new HashMap<>();
    protected List<Effect> effects = new ArrayList<>();

    protected Inventory inventory;
    protected Texture texture;

    protected Action action = null;

    protected Entity() {
    }

    public static abstract class Builder {
        protected EntityName name;
        protected Position position = Position.UNKNOWN;
        protected Texture texture;

        public Builder withName(EntityName name) {
            this.name = name;
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

    @Override
    public String toString() {
        return "Entity{" +
                "name=" + name +
                '}';
    }

    public void tick() {

    }

    public void applyEffect(Effect effect) {
        if (effect instanceof InstantEffect) {
            for (AttributeModifier modifier : effect.getModifiers()) {
                Attribute attribute = this.attributes.get(modifier.getAttributeType());
                switch (attribute.getType()) {
                    case HEALTH:
                        float newValue = attribute.getBaseValue();
                        if (modifier.getOperator().equals(Attribute.Operator.ADD)) {
                            newValue = attribute.getBaseValue() + modifier.getMagnitude();
                        }
                        else {
                            newValue = attribute.getBaseValue() * modifier.getMagnitude();
                        }
                        attribute.setBaseValue(Math.max(newValue, attributes.get(Attribute.AttributeType.MAX_HEALTH).getValue()));
                }
            }
        }
    }

    public void removeEffect(Effect effect) {
        // Get the modifiers associated with this effect
        List<AttributeModifier> modifiers = effect.getModifiers();
        // Remove them from their attributes
        for (AttributeModifier modifier : effect.getModifiers()) {
            Attribute attribute = attributes.get(modifier.getAttributeType());
            attribute.removeModifier(modifier);
        }
        this.effects.remove(effect);
    }

    public boolean hasTag(Tag tag) {
        return tags.containsKey(tag) && tags.get(tag) > 0;
    }

}
