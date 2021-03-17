package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Feature {

    protected AssetDescriptor<Texture> asset;
    protected static final Logger logger = LoggerFactory.getLogger(Feature.class);

    // TODO: This probably has to be handled precisely by the Level. Or even the GameScreen, which has the texture.
    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        Texture t = assets.getTexture(this.asset);
        batch.draw(t, x * t.getWidth(), y * t.getHeight());
    }

    public AssetDescriptor<Texture> getAsset() {
        return asset;
    }

    public boolean isWalkable(Entity entity) {
        return true;
    }

    public boolean blocksSight(Entity entity) {
        return false;
    }

}
