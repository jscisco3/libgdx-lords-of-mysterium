package com.jscisco.lom.zone.strategies;

import com.jscisco.lom.zone.Tile;

public interface GenerationStrategy {

    Tile[][] generate(int width, int height, boolean stairsUp, boolean stairsDown);

}
