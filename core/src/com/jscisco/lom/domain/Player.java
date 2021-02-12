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
    public Command getNextCommand() {
        Command c = this.command;
        this.command = null;
        return c;
    }
}
