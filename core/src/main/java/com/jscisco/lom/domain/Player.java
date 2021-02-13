package com.jscisco.lom.domain;

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
    public Action getNextCommand() {
        Action c = this.action;
        this.action = null;
        return c;
    }
}
