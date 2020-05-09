package com.jscisco.lom.shelf.domain;

import com.jscisco.lom.shelf.assets.Assets;

public class Wall extends Terrain {

    public Wall() {
        super(Assets.Glyphs.WALL, false, false);
    }
}
