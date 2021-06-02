package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.entity.Entity;

public class Floor extends Feature {

    public Floor() {
        this.glyph = Assets.floor;
    }

    @Override
    public boolean isWalkable(Entity entity) {
        return true;
    }
}
