package com.jscisco.lom.domain.actor;

public class ActorFactory {

    public static Player player() {
        return (Player) new Player.Builder()
                .withName(ActorName.of("Player"))
                .build();
    }

}
