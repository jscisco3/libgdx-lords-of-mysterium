package com.jscisco.lom.map;

import squidpony.squidmath.RNG;

public interface InitialMapBuilder {
    void build_map(RNG rng, BuildData buildData);
}
