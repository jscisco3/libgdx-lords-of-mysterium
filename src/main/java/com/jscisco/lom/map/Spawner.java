package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.random.RNGProvider;
import com.jscisco.lom.raws.RawMaster;
import squidpony.squidmath.RNG;

import java.util.ArrayList;
import java.util.List;

public class Spawner {
    private RawMaster raws;
    private RNG rng;

    public Spawner(RawMaster raws, RNGProvider rngProvider) {
        this.raws = raws;
        this.rng = rngProvider.getRng();
    }

    public void spawnRoom(Level level, Rect room) {
        // Get list of possible points (floors)
        List<Position> region = new ArrayList<>();
        room.points().forEach(p -> {
            if (level.getTile(p).getFeature() == Feature.FLOOR) {
                region.add(p);
            }
        });
        // Pass them to spawnRegion
        spawnRegion(level, region);
    }

    /**
     * For a given region (a list of valid positions for spawning), we will spawn the appropriate NPC or Item.
     *
     * @param level  The level we are spawning in
     * @param region The valid positions for spawning
     */
    public void spawnRegion(Level level, List<Position> region) {

    }

    private void spawnNPC() {

    }

    private void spawnItem() {

    }

}
