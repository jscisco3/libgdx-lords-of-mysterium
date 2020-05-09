package com.jscisco.lom.shelf.domain;

import com.jscisco.lom.shelf.assets.Assets;

public class Floor extends Terrain {

    public Floor() {
        super(Assets.Glyphs.FLOOR, true, true);
    }
}
