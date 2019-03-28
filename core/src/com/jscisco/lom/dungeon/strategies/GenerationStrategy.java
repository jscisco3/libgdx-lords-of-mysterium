package com.jscisco.lom.dungeon.strategies;

import com.jscisco.lom.util.Size3D;

public abstract class GenerationStrategy {

    protected Size3D size;

    public GenerationStrategy(Size3D size) {
        this.size = size;
    }
}
