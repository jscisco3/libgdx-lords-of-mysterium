package com.jscisco.lom.domain.zone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Zone {

    @Id
    private UUID id = UUID.randomUUID();

    // TODO: Fetch these eagerly and initialize them when loading
    // TODO: What about listing zone metadata in the future? Could fetch this lazily and initialize when loading game
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        level.setZone(this);
    }

    public Level getLevelById(UUID levelId) {
        return this.levels.stream().filter(level -> level.getId().equals(levelId)).findFirst().get();
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                '}';
    }
}
