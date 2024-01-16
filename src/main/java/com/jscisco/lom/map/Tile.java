package com.jscisco.lom.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;

import java.util.Set;

public class Tile {

    Feature feature = Feature.WALL;
    private boolean explored = false;

    public Tile() {
    }

    public Tile(Feature feature) {
        this.feature = feature;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public boolean isWalkable(Entity entity) {
        return Set.of(Feature.STAIRS_DOWN, Feature.STAIRS_UP, Feature.FLOOR).contains(this.feature);
    }

    public boolean blocksSight(Entity entity) {
        return this.feature == Feature.WALL;
    }

    public boolean isExplored() {
        return explored;
    }

    public void explore() {
        this.explored = true;
    }

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        feature.draw(batch, assets, x, y);
    }

}
