package com.jscisco.lom.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jscisco.lom.domain.zone.Feature;
import com.jscisco.lom.domain.zone.Floor;
import com.jscisco.lom.domain.zone.Wall;

import java.util.HashMap;
import java.util.Map;

public class Textures {

    public static Map<Class<? extends Feature>, Texture> featureTextures = new HashMap<>();

    public static Texture warriorTexture = null;
    public static Texture golem = null;


    public static void initialize() {
        featureTextures.put(Floor.class, new Texture(Gdx.files.internal("textures/features/floor.png")));
        featureTextures.put(Wall.class, new Texture(Gdx.files.internal("textures/features/wall.png")));

        warriorTexture = new Texture(Gdx.files.internal("textures/entities/warrior.png"));
        golem = new Texture(Gdx.files.internal("textures/entities/golem.png"));
    }

}
