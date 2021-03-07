package com.jscisco.lom.domain.kingdom;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Hero;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    private final Name name;
    private List<Hero> heroes;

    private final Inn inn = new Inn();

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
}
