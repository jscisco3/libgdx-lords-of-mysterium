package com.jscisco.lom.domain;

public class Terrain {
    boolean blocksSight;
    boolean walkable;

    private Terrain() {}

    public static Terrain floor() {
        Terrain t = new Terrain();
        t.blocksSight = false;
        t.walkable = true;
        return t;
    }

    public static Terrain wall() {
        Terrain t = new Terrain();
        t.blocksSight = true;
        t.walkable = false;
        return t;
    }
}
