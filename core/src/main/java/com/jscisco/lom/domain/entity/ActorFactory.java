package com.jscisco.lom.domain.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ActorFactory {

    public static Player player() {
        return (Player) new Player.Builder()
                .withName(EntityName.of("Player"))
                .withTexture(new Texture(Gdx.files.internal("textures/entities/warrior.png")))
                .build();
    }

}
