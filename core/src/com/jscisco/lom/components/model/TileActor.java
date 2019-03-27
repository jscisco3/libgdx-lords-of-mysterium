package com.jscisco.lom.components.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileActor extends Actor {

    private TextureRegion region;

    public TileActor(TextureRegion region) {
        this.region = region;
        this.setWidth(region.getRegionWidth());
        this.setHeight(region.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, getRotation());
    }

    public TextureRegion getRegion() {
        return region;
    }
}
