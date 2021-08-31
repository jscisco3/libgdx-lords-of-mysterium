package com.jscisco.lom.domain.zone;

import com.jscisco.lom.application.Assets;

// TODO: Perhaps a feature called... level transition :D... is more appropriate? And then the factory creates it with appropriate asset
public class StairsDown extends Feature {
    public StairsDown() {
        this.glyph = Assets.stairsDown;
    }
}
