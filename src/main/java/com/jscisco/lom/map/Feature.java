package com.jscisco.lom.map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.application.Assets;

public enum Feature {

    WALL("wall"),
    STAIRS_DOWN("stairsDown"),
    STAIRS_UP("stairsUp"),
    FLOOR("floor");

    public final String glyph;

    Feature(String glyph) {
        this.glyph = glyph;
    }

    public void draw(SpriteBatch batch, Assets assets, int x, int y) {
        TextureRegion t = assets.getTextureRegion(this.glyph);
        Sprite s = new Sprite(t);
        s.setPosition(s.getWidth() * x, s.getHeight() * y);
        s.draw(batch);
    }
}
