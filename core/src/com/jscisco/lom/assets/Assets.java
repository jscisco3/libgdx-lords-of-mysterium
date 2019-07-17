package com.jscisco.lom.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class Assets {

    public static TextureAtlas atlas;

    // Terrain
    public static TextureRegion floor;
    public static TextureRegion wall;
    public static TextureRegion stairsUp;
    public static TextureRegion stairsDown;

    // Jobs
    public static TextureRegion player;
    public static TextureRegion warrior;
    public static TextureRegion wizard;
    public static TextureRegion rogue;

    // NPCs
    public static TextureRegion rat;
    public static TextureRegion snuugz;

    // Items
    public static TextureRegion sword;
    public static TextureRegion body_armor;
    public static TextureRegion boots;
    public static TextureRegion cloak;
    public static TextureRegion gloves;
    public static TextureRegion helmet;
    public static TextureRegion ring;

    public static Map<String, TextureRegion> textureMap = new HashMap<>();


    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("../out/images/images.atlas"));

        // Terrain
        floor = atlas.findRegion("floor");
        textureMap.put("floor", floor);
        wall = atlas.findRegion("wall");
        textureMap.put("wall", wall);
        stairsUp = atlas.findRegion("stairs_up");
        textureMap.put("stairs_up", stairsUp);
        stairsDown = atlas.findRegion("stairs_down");
        textureMap.put("stairs_down", stairsDown);

        // Jobs
        player = atlas.findRegion("player");
        textureMap.put("player", player);
        warrior = atlas.findRegion("warrior");
        textureMap.put("warrior", warrior);
        wizard = atlas.findRegion("wizard");
        textureMap.put("wizard", wizard);
        rogue = atlas.findRegion("rogue");
        textureMap.put("rogue", rogue);

        // Characters
        rat = atlas.findRegion("rat");
        snuugz = atlas.findRegion("snuugz");

        // Items
        sword = atlas.findRegion("sword");
        body_armor = atlas.findRegion("body_armor");
        boots = atlas.findRegion("boots");
        cloak = atlas.findRegion("cloak");
        gloves = atlas.findRegion("gloves");
        helmet = atlas.findRegion("helmet");
        ring = atlas.findRegion("ring");
    }

    public static void dispose() {
        atlas.dispose();
    }

}
