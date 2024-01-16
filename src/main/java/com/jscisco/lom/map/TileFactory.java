package com.jscisco.lom.map;

public class TileFactory {

    public static Tile floorTile() {
        return new Tile(Feature.FLOOR);
    }

    public static Tile wallTile() {
        return new Tile(Feature.WALL);
    }

    public static Tile stairsDown() {
        return new Tile(Feature.STAIRS_DOWN);
    }

    public static Tile stairsUp() {
        return new Tile(Feature.STAIRS_UP);
    }

}
