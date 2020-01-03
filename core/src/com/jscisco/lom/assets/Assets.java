package com.jscisco.lom.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.EnumMap;
import java.util.Map;

public class Assets {

    public enum Glyphs {
        FLOOR,
        WALL,
        STAIRS_UP,
        STAIRS_DOWN,

        MOUSE_TARGET,

        WARRIOR,
        WIZARD,
        ROGUE,

        RAT,
        SNUUGZ,

        SWORD,
        BODY_ARMOR,
        CLOAK,
        BOOTS,
        GLOVES,
        HELMET,
        RING
    }

    public static TextureAtlas atlas;

    public static final Map<Glyphs, TextureRegion> textureMap = new EnumMap<>(Glyphs.class);

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("../out/images/images.atlas"));

        // Terrain
        textureMap.put(Glyphs.FLOOR, atlas.findRegion("floor"));
        textureMap.put(Glyphs.WALL, atlas.findRegion("wall"));
        textureMap.put(Glyphs.STAIRS_UP, atlas.findRegion("stairs_up"));
        textureMap.put(Glyphs.STAIRS_DOWN, atlas.findRegion("stairs_down"));

        // Utility
        textureMap.put(Glyphs.MOUSE_TARGET, atlas.findRegion("mouse_target"));

        // Jobs
        textureMap.put(Glyphs.WARRIOR, atlas.findRegion("warrior"));
        textureMap.put(Glyphs.WIZARD, atlas.findRegion("wizard"));
        textureMap.put(Glyphs.ROGUE, atlas.findRegion("rogue"));

        // Characters
        textureMap.put(Glyphs.RAT, atlas.findRegion("rat"));
        textureMap.put(Glyphs.SNUUGZ, atlas.findRegion("snuugz"));

        // Items
        textureMap.put(Glyphs.SWORD, atlas.findRegion("sword"));
        textureMap.put(Glyphs.BODY_ARMOR, atlas.findRegion("body_armor"));
        textureMap.put(Glyphs.CLOAK, atlas.findRegion("cloak"));
        textureMap.put(Glyphs.BOOTS, atlas.findRegion("boots"));
        textureMap.put(Glyphs.GLOVES, atlas.findRegion("gloves"));
        textureMap.put(Glyphs.HELMET, atlas.findRegion("helmet"));
        textureMap.put(Glyphs.RING, atlas.findRegion("ring"));
    }

    public static void dispose() {
        atlas.dispose();
    }
}
