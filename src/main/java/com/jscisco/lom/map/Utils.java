package com.jscisco.lom.map;

import com.jscisco.lom.domain.zone.TileFactory;

public class Utils {
    public static void applyRoomToLevel(Level level, Room room) {
        room.points().forEach(p -> {
            level.setTile(p.getX(), p.getY(), TileFactory.floorTile());
        });
    }
}
