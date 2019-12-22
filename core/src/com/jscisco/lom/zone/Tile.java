package com.jscisco.lom.zone;

import com.jscisco.lom.terrain.TerrainRepository;
import com.jscisco.lom.terrain.Terrain;

public class Tile {

    private Terrain terrain;
    private boolean seen;
    private boolean inFov;

    public Tile() {
        this.terrain = TerrainRepository.FLOOR;
        this.seen = false;
        this.inFov = false;
    }

    public Tile(Terrain terrain) {
        this.terrain = terrain;
        this.seen = false;
        this.inFov = false;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isInFov() {
        return inFov;
    }

    public void setInFov(boolean inFov) {
        this.inFov = inFov;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
