package com.jscisco.lom.repositories;

import com.jscisco.lom.terrain.Floor;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.terrain.Wall;

public class TerrainRepository {

    public static Terrain FLOOR = new Floor();
    public static Terrain WALL = new Wall();

}
