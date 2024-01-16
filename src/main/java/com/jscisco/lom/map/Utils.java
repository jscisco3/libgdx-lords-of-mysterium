package com.jscisco.lom.map;

public class Utils {
    public static void applyRoomToLevel(Level level, Rect room) {
        room.points().forEach(p -> level.setTile(p.getX(), p.getY(), TileFactory.floorTile()));
    }
}
