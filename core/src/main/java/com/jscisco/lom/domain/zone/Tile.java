package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    Feature feature = FeatureFactory.FLOOR;
    Entity occupant = null;
    List<Item> items = new ArrayList<>();

    public Tile() {
    }

    public Tile(Feature feature) {
        this.feature = feature;
    }

    private boolean explored = false;

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

    public boolean removeItem(Item item) {
        return this.items.remove(item);
    }

    public boolean isWalkable(Entity entity) {
        return feature.isWalkable(entity);
    }

    public boolean blocksSight(Entity entity) {
        return feature.blocksSight(entity);
    }

    public boolean isExplored() {
        return explored;
    }

    public void explore() {
        this.explored = true;
    }

    public boolean hasItems() {
        return !this.items.isEmpty();
    }

    public void draw(SpriteBatch batch, Assets assets, int x, int y, boolean isInSight) {
        feature.draw(batch, assets, x, y);
        if (isInSight) {
            if (!items.isEmpty()) {
                items.get(items.size() - 1).draw(batch, assets, x, y);
            }
            if (isOccupied()) {
                occupant.draw(batch, assets);
            }
        }
    }

}
