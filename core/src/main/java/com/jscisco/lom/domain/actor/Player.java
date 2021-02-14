package com.jscisco.lom.domain.actor;

import com.jscisco.lom.domain.action.Action;

public class Player extends Actor {

    private Player() {
        super();
    }

    public static class Builder extends Actor.Builder {

        public Player build() {
            Player player = new Player();
            player.name = this.name;
            player.attributes = this.attributes;
            player.position = this.position;
            return player;
        }
    }

    @Override
    public Action nextAction() {
        Action c = this.action;
        this.action = null;
        return c;
    }
}
