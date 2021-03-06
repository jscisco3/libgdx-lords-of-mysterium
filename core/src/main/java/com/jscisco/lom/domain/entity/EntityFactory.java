package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Assets;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.InstantEffect;

public class EntityFactory {

    public static Hero player() {
        return new Hero.Builder()
                .withName(EntityName.of("Player"))
                .withAsset(Assets.warrior)
                .build();
    }

    public static NPC golem() {
        NPC golem = new NPC.Builder()
                .withName(EntityName.of("Golem"))
                .withAsset(Assets.golem)
                .withController(new RestAIController())
                .build();

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
