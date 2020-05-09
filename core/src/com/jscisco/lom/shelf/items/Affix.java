package com.jscisco.lom.shelf.items;

import java.util.Optional;

public class Affix {
    private Optional<String> nameModifier;

    public Affix(String nameModifier) {
        this.nameModifier = Optional.of(nameModifier);
    }
}
