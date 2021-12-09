package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Feature {

    protected String glyph;
    protected static final Logger logger = LoggerFactory.getLogger(Feature.class);

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        TextureRegion t = assets.getTextureRegion(this.glyph);
        Sprite s = new Sprite(t);
        s.setPosition(s.getWidth() * x, s.getHeight() * y);
        s.draw(batch);
    }

    public String getGlyph() {
        return glyph;
    }

    public boolean isWalkable(Entity entity) {
        return true;
    }

    public boolean blocksSight(Entity entity) {
        return false;
    }

}
