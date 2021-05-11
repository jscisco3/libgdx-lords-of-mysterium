package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.EntityFactory;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL)
    private List<Level> levels = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "save_game_id", nullable = false)
    private SaveGame saveGame;

    protected Zone() {

    }

    public Zone(int depth) {
        for (int i = 0; i < depth; i++) {
            Level level = new Level();
            addLevel(level);
        }
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

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                '}';
    }
}
