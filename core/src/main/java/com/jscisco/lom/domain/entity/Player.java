package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public class Player extends Entity {

    private Player() {
        super();
    }

    public static class Builder extends Entity.Builder<Builder> {

        public Player build() {
            Player player = new Player();
            player.name = this.name;
            player.position = this.position;
            player.texture = this.texture;
            return player;
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Action nextAction() {
        Action c = this.action;
        this.action = null;
        return c;
    }
}
