package com.jscisco.lom.domain;

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.zone.Feature;

public class Wall extends Feature {

    @Override
    public boolean isWalkable(Entity entity) {
        return false;
    }
}
