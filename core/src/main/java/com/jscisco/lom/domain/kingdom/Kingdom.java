package com.jscisco.lom.domain.kingdom;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.Hero;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    // TODO: UUID
    private Long id;

    private Name name;

    private SaveGame saveGame;

    private List<Hero> heroes = new ArrayList<>();

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

    @Override
    public String toString() {
        return "Kingdom{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
