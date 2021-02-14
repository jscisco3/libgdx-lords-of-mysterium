package com.jscisco.lom.domain.actor;

public class ActorName {

    final String name;

    private ActorName(String name) {
        this.name = name;
    }

    public static ActorName of(String name) {
        return new ActorName(name);
    }

}
