package com.jscisco.lom.domain;

public class Floor extends Feature {

    @Override
    public boolean isWalkable(Actor actor) {
        return true;
    }
}
