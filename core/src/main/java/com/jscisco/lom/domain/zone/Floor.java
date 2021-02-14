package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.actor.Actor;

public class Floor extends Feature {

    @Override
    public boolean isWalkable(Actor actor) {
        return true;
    }
}
