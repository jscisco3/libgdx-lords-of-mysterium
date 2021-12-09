package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;

public class Wall extends Feature {

    public Wall() {
        this.glyph = Assets.wall;
    }

    @Override
    public boolean isWalkable(Entity entity) {
//        return true;
        return false;
    }

    @Override
    public boolean blocksSight(Entity entity) {
//        return false;
        return true;
    }
}
