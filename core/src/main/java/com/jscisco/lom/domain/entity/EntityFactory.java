package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;

public class EntityFactory {

    public static Hero player() {
        Hero hero = new Hero.Builder()
                .withName(Name.of("Player"))
                .withGlyph(Assets.warrior)
                .build();

        return hero;
    }

    public static NPC golem() {

        NPC golem = new NPC.Builder()
                .withName(Name.of("Golem"))
                .withGlyph(Assets.golem)
                .build();
        golem.setAiController(new WanderAIController(golem));

        return golem;
    }
}
