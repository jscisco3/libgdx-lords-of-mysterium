package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BuildData {
    private final Logger logger = LoggerFactory.getLogger(BuildData.class);
    private List<Entity> spawnList = new ArrayList<>();
    private Level level;
    private Position startingPosition = null;
    private List<Rect> rooms = new ArrayList<>();
    private List<Level> history = new ArrayList<>();

    BuildData(int depth, int width, int height) {
        this.level = new Level(depth, width, height);
    }

    public Level getLevel() {
        return level;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public void takeSnapshot() {
        // Clone the level
        logger.info("Taking snapshot...");
        this.history.add(this.level.clone());
    }

    public void setRooms(List<Rect> rooms) {
        this.rooms = rooms;
    }

    public List<Rect> getRooms() {
        return rooms;
    }

    public List<Level> getHistory() {
        return history;
    }
}
