package com.jscisco.lom.domain;

import com.jscisco.lom.domain.actor.Actor;
import com.jscisco.lom.domain.zone.Feature;

public class Wall extends Feature {

    @Override
    public boolean isWalkable(Actor actor) {
        return false;
    }
}
