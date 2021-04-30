package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.InstantEffect;

public class EntityFactory {

    public static Hero player() {
        Hero hero = new Hero.Builder()
                .withName(Name.of("Player"))
                .withAsset(Assets.warrior)
                .build();

        hero.applyEffect(
                new InstantEffect()
                        .addModifier(new AttributeModifier()
                                .forAttribute(hero.getAttributes().getMaxHealth())
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(hero.getAttributes().getHealth())
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(hero.getAttributes().getLightRadius())
                                .withMagnitude(10f)
                                .withOperator(Attribute.Operator.OVERRIDE))
        );
        return hero;
    }

    public static NPC golem() {

        NPC golem = new NPC.Builder()
                .withName(Name.of("Golem"))
                .withAsset(Assets.golem)
                .build();
        golem.setAiController(new HunterSeekerAI(golem));

        golem.applyEffect(new InstantEffect()
                .addModifier(new AttributeModifier()
                        .forAttribute(golem.attributes.getMaxHealth())
                        .withMagnitude(100f)
                        .withOperator(Attribute.Operator.OVERRIDE))
                .addModifier(new AttributeModifier()
                        .forAttribute(golem.attributes.getHealth())
                        .withMagnitude(100f)
                        .withOperator(Attribute.Operator.OVERRIDE)));
        return golem;
    }
}
