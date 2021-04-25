package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Glyph;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Feature {

    protected Glyph asset;
    protected static final Logger logger = LoggerFactory.getLogger(Feature.class);

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        TextureRegion t = assets.getTextureRegion(this.asset);
        Sprite s = new Sprite(t);
        s.setPosition(s.getWidth() * x, s.getHeight() * y);
        s.draw(batch);
    }

    public Glyph getAsset() {
        return asset;
    }

    public boolean isWalkable(Entity entity) {
        return true;
    }

    public boolean blocksSight(Entity entity) {
        return false;
    }

}
