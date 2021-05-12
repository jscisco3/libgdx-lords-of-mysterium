package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Description;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.attribute.InstantEffect;
import squidpony.FakeLanguageGen;

public class EntityFactory {

    public static Hero player() {
        Hero hero = new Hero.Builder()
                .withName(Name.of(FakeLanguageGen.FANCY_FANTASY_NAME.word(true)))
                .withAsset(Assets.warrior)
                .build();

        //        attributes.put(AttributeDefinition.HEALTH, new Attribute(Name.of("Health"), Description.of("Current Health")));
//        attributes.put(AttributeDefinition.MAX_HEALTH, new Attribute(Name.of("Max Health"), Description.of("Maximum Health")));
//        attributes.put(AttributeDefinition.LIGHT_RADIUS, new Attribute(Name.of("Light Radius"), Description.of("Light Radius")));

        hero.getAttributes().addAttribute(AttributeSet.AttributeDefinition.HEALTH, new Attribute(Name.of("Health"), Description.of("Current Health")));
        hero.getAttributes().addAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH, new Attribute(Name.of("Max Health"), Description.of("Max Health")));
        hero.getAttributes().addAttribute(AttributeSet.AttributeDefinition.LIGHT_RADIUS, new Attribute(Name.of("Light Radius"), Description.of("Light Radius")));

        hero.applyEffect(
                new InstantEffect()
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.LIGHT_RADIUS)
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

        golem.getAttributes().addAttribute(AttributeSet.AttributeDefinition.HEALTH, new Attribute(Name.of("Health"), Description.of("Current Health")));
        golem.getAttributes().addAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH, new Attribute(Name.of("Max Health"), Description.of("Max Health")));
        golem.getAttributes().addAttribute(AttributeSet.AttributeDefinition.LIGHT_RADIUS, new Attribute(Name.of("Light Radius"), Description.of("Light Radius")));

        golem.applyEffect(
                new InstantEffect()
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                                .withMagnitude(100f)
                                .withOperator(Attribute.Operator.OVERRIDE)
                        )
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.LIGHT_RADIUS)
                                .withMagnitude(10f)
                                .withOperator(Attribute.Operator.OVERRIDE))
        );
        return golem;
    }
}
