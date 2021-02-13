package com.jscisco.lom.domain;

public class Wall extends Feature {

    @Override
    public boolean isWalkable(Actor actor) {
        return false;
    }
}
