package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.domain.entity.Entity;

public abstract class Feature {

    protected Texture texture;

    public void draw(SpriteBatch batch, int x, int y) {
        batch.draw(texture, x * texture.getWidth(), y * texture.getHeight());
    }

    public boolean isWalkable(Entity entity) {
        return true;
    }

}
