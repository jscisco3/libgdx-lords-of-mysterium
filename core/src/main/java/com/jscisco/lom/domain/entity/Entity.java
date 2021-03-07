package com.jscisco.lom.domain.entity;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
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

    protected Name name;
    protected Level level;
    protected Position position;

    protected Map<Tag, Integer> tags = new HashMap<>();
    protected AttributeSet attributes = new AttributeSet();
    protected List<Effect> effects = new ArrayList<>();

    protected Inventory inventory;
    protected AssetDescriptor<Texture> asset;

    protected Action action = null;

    protected Entity() {
    }

    public static abstract class Builder<T extends Builder<T>> {
        protected Name name;
        protected Position position = Position.UNKNOWN;
        protected AssetDescriptor<Texture> asset;
        protected AttributeSet attributeSet;

        @SuppressWarnings("unchecked")
        public T withName(Name name) {
            this.name = name;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withPosition(Position position) {
            this.position = position;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withAsset(AssetDescriptor<Texture> asset) {
            this.asset = asset;
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        public T withAttributes(AttributeSet attributeSet) {
            this.attributeSet = attributeSet;
            return (T) this;
        }

        public abstract Entity build();

    }

    public Name getName() {
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

    public Level getLevel() {
        return level;
    }

    public void draw(SpriteBatch batch, Assets assets) {
        Texture t = assets.getTexture(this.asset);
        batch.draw(t, position.getX() * t.getWidth(), position.getY() * t.getHeight());
    }

    public abstract Action nextAction();

    @Override
    public String toString() {
        return "Entity{" +
                "name=" + name +
                '}';
    }

    public void tick() {
        // Each turn, we should tick effects
        List<Effect> expiredEffects = new ArrayList<>();
        for (Effect effect : this.effects) {
            effect.tick();
            if (effect.isExpired()) {
                expiredEffects.add(effect);
            }
        }
        for (Effect effect : expiredEffects) {
            removeEffect(effect);
        }
    }

    public void applyEffect(Effect effect) {
        if (effect instanceof InstantEffect) {
            // Apply them immediately.
            effect.apply(this.attributes);
        }
        // Otherwise, it is an effect that makes changes over time. Thus, we add it to the entities active effects.
        // and toggle it on the appropriate modifiers
        // TODO: should effects be on the attribute set? Not necessarily.
        else {
            this.effects.add(effect);
            effect.apply(attributes);
        }
    }

    public void removeEffect(Effect effect) {
        // Here, we need to remove the modifiers that are on the attribute
        for (AttributeModifier modifier : effect.getModifiers()) {
            modifier.getAttribute().removeModifier(modifier);
        }
        // Then, we remove the effect
        this.effects.remove(effect);
    }

    public boolean hasTag(Tag tag) {
        return tags.containsKey(tag) && tags.get(tag) > 0;
    }

    public AttributeSet getAttributes() {
        return this.attributes;
    }

    public void onDied() {
        this.applyEffect(new DurationEffect()
                .withDuration(Duration.permanent())
                .grantTag(Tag.DEAD));
    }
}
