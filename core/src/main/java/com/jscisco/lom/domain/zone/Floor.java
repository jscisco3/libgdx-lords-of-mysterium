package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.entity.Entity;

public class Floor extends Feature {

    @Override
    public boolean isWalkable(Entity entity) {
        return true;
    }
}
