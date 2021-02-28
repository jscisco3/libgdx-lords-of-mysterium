package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.application.Textures;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Feature {

    protected Texture texture = Textures.featureTextures.get(this.getClass());
    protected static final Logger logger = LoggerFactory.getLogger(Feature.class);

    public void draw(SpriteBatch batch, int x, int y) {
        batch.draw(texture, x * texture.getWidth(), y * texture.getHeight());
    }

    public boolean isWalkable(Entity entity) {
        return true;
    }

}
