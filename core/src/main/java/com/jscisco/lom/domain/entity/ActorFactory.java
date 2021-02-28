package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Textures;

public class ActorFactory {

    public static Player player() {
        return (Player) new Player.Builder()
                .withName(EntityName.of("Player"))
                .withTexture(Textures.warriorTexture)
                .build();
    }

}
