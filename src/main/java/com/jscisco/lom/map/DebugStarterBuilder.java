package com.jscisco.lom.map;

import com.jscisco.lom.domain.zone.FeatureFactory;
import com.jscisco.lom.domain.zone.Tile;
import squidpony.squidmath.RNG;

import java.util.stream.IntStream;

/**
 * This builder will generate an entirely empty dungeon, surrounded by walls.
 */
public class DebugStarterBuilder implements InitialMapBuilder {
    public DebugStarterBuilder() {

    }

    @Override
    public void buildMap(RNG rng, BuildData buildData) {
        IntStream.range(0, buildData.getLevel().width - 1).forEach(x -> {
            IntStream.range(0, buildData.getLevel().height - 1).forEach(y -> {
                buildData.getLevel().setTile(x, y, new Tile(FeatureFactory.FLOOR));
            });
        });
    }
}
