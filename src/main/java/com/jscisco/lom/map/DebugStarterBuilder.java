package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.zone.FeatureFactory;
import com.jscisco.lom.domain.zone.Tile;
import squidpony.squidmath.RNG;

import java.util.List;
import java.util.stream.IntStream;

/**
 * This builder will generate an entirely empty dungeon, surrounded by walls.
 */
public class DebugStarterBuilder implements InitialMapBuilder {
    public DebugStarterBuilder() {

    }

    @Override
    public void initializeMap(RNG rng, BuildData buildData) {
        Rect room = new Rect(Position.of(1, 1), buildData.getLevel().width - 1, buildData.getLevel().height - 1);
        buildData.setRooms(List.of(room));
        Utils.applyRoomToLevel(buildData.getLevel(), room);
//        IntStream.range(1, buildData.getLevel().width - 1).forEach(x -> {
//            IntStream.range(1, buildData.getLevel().height - 1).forEach(y -> {
//                buildData.getLevel().setTile(x, y, new Tile(FeatureFactory.FLOOR));
//            });
//        });
        buildData.takeSnapshot();
    }
}
