package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;

public class Tile {

    Feature feature = FeatureFactory.FLOOR;
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

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        feature.draw(batch, assets, x, y);
    }

}
