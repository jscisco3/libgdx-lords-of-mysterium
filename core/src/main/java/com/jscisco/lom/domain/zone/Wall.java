package com.jscisco.lom.domain.zone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.domain.entity.Entity;

public class Wall extends Feature {

    public Wall() {
        this.texture = new Texture(Gdx.files.internal("textures/features/wall.png"));
    }

    @Override
    public boolean isWalkable(Entity entity) {
        return false;
    }
}
