package com.jscisco.lom.map;

import com.jscisco.lom.raws.RawMaster;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class BuilderChain {
    private InitialMapBuilder starter = null;
    private final List<MetaMapBuilder> metaMapBuilders = new ArrayList<>();
    private final BuildData buildData;

    public BuilderChain(int depth, int width, int height) {
        this.buildData = new BuildData(depth, width, height);
    }

    public BuilderChain startWith(InitialMapBuilder starter) {
        this.starter = starter;
        return this;
    }

    public BuilderChain with(MetaMapBuilder builder) {
        this.metaMapBuilders.add(builder);
        return this;
    }

    public void build(RNG rng, RawMaster rawMaster) {
        if (this.starter == null) {
            throw new IllegalStateException("Cannot run a builder chain without a starter.");
        }
        starter.initializeMap(rng, this.buildData);
        for (MetaMapBuilder meta : this.metaMapBuilders) {
            meta.mutateMap(rng, this.buildData, rawMaster);
        }
    }

    public BuildData getBuildData() {
        return buildData;
    }
}
