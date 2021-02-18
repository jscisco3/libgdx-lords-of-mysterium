package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.domain.entity.Entity;

public abstract class Feature {

    protected Texture texture;

    public boolean isWalkable(Entity entity) {
        return true;
    }

}
