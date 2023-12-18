package com.jscisco.lom.map;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuildData {
    private List<Entity> spawnList = new ArrayList<>();
    private Level level;
    private Position startingPosition = null;
    private List<Room> rooms = new ArrayList<>();
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
}
