package com.jscisco.lom.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static TextureAtlas atlas;
    public static TextureRegion floor;
    public static TextureRegion wall;

    public static TextureRegion player;

    public static void load() {
        atlas = new TextureAtlas(Gdx.files.internal("../out/images/images.atlas"));
        floor = atlas.findRegion("floor");
        wall = atlas.findRegion("wall");
        player = atlas.findRegion("player");
    }

    public static void dispose() {
        atlas.dispose();
    }

}
