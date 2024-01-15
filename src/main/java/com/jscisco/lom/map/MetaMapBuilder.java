package com.jscisco.lom.map;

import com.jscisco.lom.raws.RawMaster;
import squidpony.squidmath.RNG;

public interface MetaMapBuilder {
    void mutateMap(RNG rng, BuildData buildData, RawMaster raws);
}
