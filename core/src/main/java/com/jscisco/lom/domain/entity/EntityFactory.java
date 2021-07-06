package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityFactory {

    private static final Logger logger = LoggerFactory.getLogger(EntityFactory.class);

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

    public static NPC fromEntityDefinition(EntityDefinition definition) {
        NPC npc = new NPC.Builder()
                .withName(Name.of(definition.name))
                .withGlyph(definition.glyph)
                .build();

        switch (definition.ai) {
            case "WanderAI" -> npc.setAiController(new WanderAIController(npc));
            case "HunterSeekerAI" -> npc.setAiController(new HunterSeekerAI(npc));
            default -> {
                logger.warn("No AI definition matching for: " + definition.ai);
                npc.setAiController(new RestAIController(npc));
            }
        }
        return npc;
    }
}
