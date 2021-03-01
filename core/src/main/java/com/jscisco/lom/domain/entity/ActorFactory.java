package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Textures;

public class ActorFactory {

    public static Player player() {
        return new Player.Builder()
                .withName(EntityName.of("Player"))
                .withTexture(Textures.warriorTexture)
                .build();
    }

    public static NPC golem() {
        return new NPC.Builder()
                .withName(EntityName.of("Golem"))
                .withTexture(Textures.golem)
                .withController(new RestAIController())
                .build();
    }

}
