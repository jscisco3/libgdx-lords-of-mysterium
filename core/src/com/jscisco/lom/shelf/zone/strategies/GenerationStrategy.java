package com.jscisco.lom.shelf.zone.strategies;

import com.jscisco.lom.shelf.zone.Tile;

public interface GenerationStrategy {

    Tile[][] generate(int width, int height, boolean stairsUp, boolean stairsDown);

}
