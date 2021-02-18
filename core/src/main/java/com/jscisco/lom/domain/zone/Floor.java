package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.domain.entity.Entity;

public class Floor extends Feature {

    public Floor() {
        this.texture = new Texture(Gdx.files.internal("textures/features/floor.png"));
    }

    @Override
    public boolean isWalkable(Entity entity) {
        return true;
    }
}
