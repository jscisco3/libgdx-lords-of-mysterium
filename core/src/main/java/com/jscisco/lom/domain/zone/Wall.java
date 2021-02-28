package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.entity.Entity;

public class Wall extends Feature {

    @Override
    public boolean isWalkable(Entity entity) {
        return false;
    }
}
