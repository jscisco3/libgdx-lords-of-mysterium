package com.jscisco.lom.map;

import squidpony.squidmath.RNG;

public interface InitialMapBuilder {
    void initializeMap(RNG rng, BuildData buildData);
}
