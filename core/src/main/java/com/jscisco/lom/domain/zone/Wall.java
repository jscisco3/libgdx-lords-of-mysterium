package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;

public class Wall extends Feature {

    public Wall() {
        this.asset = Assets.wall;
    }

    @Override
    public boolean isWalkable(Entity entity) {
        return false;
    }

    @Override
    public boolean blocksSight(Entity entity) {
        return true;
    }
}
