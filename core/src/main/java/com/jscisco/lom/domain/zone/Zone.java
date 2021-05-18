package com.jscisco.lom.domain.zone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "zone_sequence",
        sequenceName = "zone_sequence",
        initialValue = 1,
        allocationSize = 1
)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_sequence")
    private Long id;

    @OneToMany(mappedBy = "zone", cascade = {CascadeType.MERGE})
    private List<Level> levels = new ArrayList<>();

    public Zone() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                '}';
    }
}
