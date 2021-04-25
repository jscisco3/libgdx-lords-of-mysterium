package com.jscisco.lom.application;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.domain.Glyph;

public class Assets {

    private final AssetManager assetManager;

    /**
     * Assets go here
     */

    public static final AssetDescriptor<TextureAtlas> atlas = new AssetDescriptor<TextureAtlas>("packed/assets.atlas", TextureAtlas.class);

    // Terrain features
    public static final Glyph floor = Glyph.of("floor");
    public static final Glyph wall = Glyph.of("wall");
    public static final Glyph stairsDown = Glyph.of("stairsDown");
    public static final Glyph stairsUp = Glyph.of("stairsUp");
    // Entities
    public static final Glyph warrior = Glyph.of("warrior");
    public static final Glyph golem = Glyph.of("golem");
    // Items
    public static final Glyph sword = Glyph.of("sword");
    public static final Glyph ring = Glyph.of("ring");
    // Kingdom screen
    public static final String background = "parchmentAncient";
    public static final String inn = "inn";
    public static final String portal = "skull";

    public Assets() {
        this.assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load() {
        assetManager.load(atlas);
    }

    public TextureRegion getTextureRegion(String region) {
        return assetManager.get(atlas).findRegion(region);
    }

    public TextureRegion getTextureRegion(Glyph glyph) {
        return assetManager.get(atlas).findRegion(glyph.get());
    }

    public void dispose() {
        this.assetManager.dispose();
    }
}
