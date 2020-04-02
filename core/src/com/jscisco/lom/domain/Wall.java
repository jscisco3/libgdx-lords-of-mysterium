package com.jscisco.lom.domain;

import com.jscisco.lom.assets.Assets;

public class Wall extends Terrain {

    public Wall() {
        super(Assets.Glyphs.WALL, false, false);
    }
}
