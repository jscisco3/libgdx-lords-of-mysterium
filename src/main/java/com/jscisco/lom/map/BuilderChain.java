package com.jscisco.lom.map;


import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class BuilderChain {
    private InitialMapBuilder starter = null;
    private final List<MetaMapBuilder> metaMapBuilders = new ArrayList<>();
    private BuildData buildData;

    BuilderChain data(int depth, int width, int height) {
        this.buildData = new BuildData(depth, width, height);
        return this;
    }

    BuilderChain startWith(InitialMapBuilder starter) {
        this.starter = starter;
        return this;
    }

    BuilderChain with(MetaMapBuilder builder) {
        this.metaMapBuilders.add(builder);
        return this;
    }

    // TODO: Raws
    void build(RNG rng) {
        if (this.starter == null) {
            throw new IllegalStateException("Cannot run a builder chain without a starter.");
        }
        starter.build_map(rng, this.buildData);
        for (MetaMapBuilder meta : this.metaMapBuilders) {
            meta.build_map(rng, this.buildData);
        }
    }

    public BuildData getBuildData() {
        return buildData;
    }
}
