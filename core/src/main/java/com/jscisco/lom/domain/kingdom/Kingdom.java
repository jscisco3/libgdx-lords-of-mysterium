package com.jscisco.lom.domain.kingdom;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.Hero;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kingdom {

    @Id
    @GeneratedValue
    @Column(name = "game_id")
    private Long id;

    @Embedded
    private Name name;

    @OneToOne
    @MapsId
    @JoinColumn(name = "game_id")
    private SaveGame saveGame;

    @Transient
    private List<Hero> heroes;

    @Transient
    private final Inn inn = new Inn();

    protected Kingdom() {
    }

    public Kingdom(Name name) {
        this.name = name;
        this.heroes = new ArrayList<>();
    }

    public Name getName() {
        return name;
    }

    // TODO: Cost, max number of heroes, etc.
    public void hireHero(Hero hero) {
        this.heroes.add(hero);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public Inn getInn() {
        return inn;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }
}
