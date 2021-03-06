package com.jscisco.lom.domain.kingdom;

import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    private final Name name;
    private List<Player> heroes;

    public Kingdom(Name name) {
        this.name = name;
        this.heroes = new ArrayList<>();
    }

    public Name getName() {
        return name;
    }
}
