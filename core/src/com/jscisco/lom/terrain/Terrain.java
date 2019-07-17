package com.jscisco.lom.terrain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Terrain {

    protected boolean walkable;
    protected boolean transparent;
    protected transient TextureRegion texture;

    public Terrain(TextureRegion texture, boolean walkable, boolean transparent) {
        this.texture = texture;
        this.walkable = walkable;
        this.transparent = transparent;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean isTransparent() {
        return transparent;
    }
}
