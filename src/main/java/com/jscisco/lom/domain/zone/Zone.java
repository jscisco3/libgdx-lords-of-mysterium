package com.jscisco.lom.domain.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Zone {

    private UUID id = UUID.randomUUID();

    private List<Level> levels = new ArrayList<>();

    public Zone() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public void addLevel(Level level) {
        this.levels.add(level);
    }

    public Level getLevelById(UUID levelId) {
        return this.levels.stream().filter(level -> level.getId().equals(levelId)).findFirst().get();
    }

    @Override
    public String toString() {
        return "Zone{" + "id=" + id + '}';
    }
}
