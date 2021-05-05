package com.jscisco.lom.domain.zone;

import com.jscisco.lom.domain.SaveGame;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Zone {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels;

    @ManyToOne
    @JoinColumn(name = "save_game_id", nullable = false)
    private SaveGame saveGame;

    protected Zone() {

    }

    public Zone(int depth) {
        this.levels = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            Level level = new Level();
            level.setZone(this);
            levels.add(level);
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

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }
}
