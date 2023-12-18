package com.jscisco.lom.domain.zone;

public class TileFactory {

    public static Tile floorTile() {
        Tile t = new Tile();
        t.feature = FeatureFactory.FLOOR;
        return t;
    }

    public static Tile wallTile() {
        Tile t = new Tile();
        t.feature = FeatureFactory.WALL;
        return t;
    }

    public static Tile stairsDown() {
        return new Tile(FeatureFactory.STAIRS_DOWN);
    }

    public static Tile stairsUp() {
        return new Tile(FeatureFactory.STAIRS_UP);
    }

}
