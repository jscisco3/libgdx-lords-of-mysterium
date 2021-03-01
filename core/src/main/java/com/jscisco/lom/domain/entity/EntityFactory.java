package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.Textures;
import com.jscisco.lom.domain.attribute.Attribute;
import com.jscisco.lom.domain.attribute.AttributeModifier;
import com.jscisco.lom.domain.attribute.InstantEffect;

public class EntityFactory {

    public static Player player() {
        return new Player.Builder()
                .withName(EntityName.of("Player"))
                .withTexture(Textures.warriorTexture)
                .build();
    }

    public static NPC golem() {
        NPC golem = new NPC.Builder()
                .withName(EntityName.of("Golem"))
                .withTexture(Textures.golem)
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
