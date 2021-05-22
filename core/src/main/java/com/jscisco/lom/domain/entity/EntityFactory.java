package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.Name;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.attribute.Duration;
import com.jscisco.lom.domain.attribute.DurationEffect;
import com.jscisco.lom.domain.attribute.InstantEffect;
import com.jscisco.lom.domain.attribute.PeriodicEffect;

public class EntityFactory {

    public static Hero player() {
        Hero hero = new Hero.Builder()
                .withName(Name.of("Player"))
                .withGlyph(Assets.warrior)
                .build();

        hero.attributes.initialize();

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

        hero.applyEffect(
                new PeriodicEffect()
                        .withDuration(Duration.permanent())
                        .addModifier(new AttributeModifier()
                                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
                                .withMagnitude(1f)
                                .withOperator(Attribute.Operator.ADD)
                        )
        );
        return hero;
    }

    public static NPC golem() {

        NPC golem = new NPC.Builder()
                .withName(Name.of("Golem"))
                .withGlyph(Assets.golem)
                .build();
        golem.setAiController(new WanderAIController(golem));
        golem.attributes.initialize();

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
