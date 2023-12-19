package com.jscisco.lom.map;

import squidpony.squidmath.RNG;

public interface InitialMapBuilder {
    void buildMap(RNG rng, BuildData buildData);
}
