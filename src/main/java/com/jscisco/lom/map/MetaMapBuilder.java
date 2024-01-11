package com.jscisco.lom.map;

import squidpony.squidmath.RNG;

public interface MetaMapBuilder {
    void mutateMap(RNG rng, BuildData buildData);
}
