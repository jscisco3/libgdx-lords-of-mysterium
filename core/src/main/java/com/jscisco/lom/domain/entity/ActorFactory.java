package com.jscisco.lom.domain.entity;

public class ActorFactory {

    public static Player player() {
        return (Player) new Player.Builder()
                .withName(EntityName.of("Player"))
                .build();
    }

}
