package com.jscisco.lom.domain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;

public abstract class Terrain {

    protected boolean walkable;
    protected boolean transparent;
    protected Assets.Glyphs glyph;

    public Terrain(Assets.Glyphs glyph, boolean walkable, boolean transparent) {
        this.glyph = glyph;
        this.walkable = walkable;
        this.transparent = transparent;
    }

    public TextureRegion getTexture() {
        return Assets.textureMap.get(glyph);
    }

    public Assets.Glyphs getGlyph() {
        return glyph;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public void setGlyph(Assets.Glyphs glyph) {
        this.glyph = glyph;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isTransparent() {
        return transparent;
    }
}
