package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.zone.Tile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class GenerationStrategy {

    protected int width;
    protected int height;

    public GenerationStrategy(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Tile[][] generate() {
        throw new NotImplementedException();
    }
}
