package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.actor.Actor;
import com.jscisco.lom.domain.item.Item;

import java.util.Collections;
import java.util.List;

public class Tile {

    Feature feature = FeatureFactory.FLOOR;
    Actor occupant = null;
    List<Item> items = Collections.emptyList();

    public Feature getFeature() {
        return feature;
    }

    public Actor getOccupant() {
        return occupant;
    }

    public List<Item> getItems() {
        return items;
    }

    public void occupy(Actor occupant) {
        this.occupant = occupant;
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

    public boolean isWalkable(Actor actor) {
        return feature.isWalkable(actor);
    }

}
