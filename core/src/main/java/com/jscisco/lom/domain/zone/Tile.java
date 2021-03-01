package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;

import java.util.Collections;
import java.util.List;

public class Tile {

    Feature feature = FeatureFactory.FLOOR;
    Entity occupant = null;
    List<Item> items = Collections.emptyList();

    public Feature getFeature() {
        return feature;
    }

    public Entity getOccupant() {
        return occupant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void occupy(Entity occupant) {
        this.occupant = occupant;
    }

    public void removeOccupant() {
        this.occupant = null;
    }

    public boolean isOccupied() {
        return this.occupant != null;
    }

    public boolean addItem(Item item) {
        return this.items.add(item);
    }

    public Item removeItem(int itemIndex) {
        return this.items.remove(itemIndex);
    }

    public boolean isWalkable(Entity entity) {
        return feature.isWalkable(entity);
    }

    public void draw(SpriteBatch batch, int x, int y) {
        // Draw just the feature
        feature.draw(batch, x, y);
    }

}
