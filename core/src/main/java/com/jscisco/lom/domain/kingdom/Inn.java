package com.jscisco.lom.domain.kingdom;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.entity.Hero;
import squidpony.FakeLanguageGen;

import java.util.ArrayList;
import java.util.List;

public class Inn {

    private List<Hero> availableHeroes;

    public Inn() {
        this.availableHeroes = new ArrayList<>();
    }

    public void generateHeroes(int numberToGenerate) {
        for (int i = 0; i < numberToGenerate; i++) {
            Hero h = new Hero.Builder()
                    .withName(Name.of(FakeLanguageGen.FANTASY_NAME.word(true)))
                    .withGlyph(Assets.warrior)
                    .build();
            availableHeroes.add(h);
        }
    }

    public List<Hero> getAvailableHeroes() {
        return availableHeroes;
    }
}
