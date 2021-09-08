package com.jscisco.lom.domain.entity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class EntityApplyEffectTest extends BaseEntityTest {

    @Test
    public void whenInstantEffectIsApplied_thenTheChangeHappensInstantly() {

//        float currentMaxHealth = e.attributes.getMaxHealth().getBaseValue();
//
//        Effect instantEffect = new InstantEffect()
//                .addModifier(new AttributeModifier()
//                        .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
//                        .withMagnitude(100f)
//                        .withOperator(Attribute.Operator.ADD));
//        e.applyEffect(instantEffect);
//
//        assertThat(e.attributes.getMaxHealth().getBaseValue()).isEqualTo(currentMaxHealth + 100f);
    }

    @Test
    public void whenPeriodicEffectIsApplied_thenItIsStoredOnTheEntity() {
//        AttributeModifier modifier = new AttributeModifier()
//                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
//                .withMagnitude(100f)
//                .withOperator(Attribute.Operator.ADD);
//
//        Effect periodicEffect = new PeriodicEffect()
//                .addModifier(modifier);
//
//        e.applyEffect(periodicEffect);
//        assertThat(e.effects).contains(periodicEffect);
    }

    @Test
    public void whenDurationEffectIsApplied_thenItIsStoredOnTheEntity_andItsAttributes() {
//        AttributeModifier modifier = new AttributeModifier()
//                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
//                .withMagnitude(100f)
//                .withOperator(Attribute.Operator.ADD);
//
//        Effect durationEffect = new DurationEffect()
//                .addModifier(modifier);
//
//        e.applyEffect(durationEffect);
//        assertThat(e.effects).contains(durationEffect);
//        assertThat(e.attributes.getHealth().getModifiers()).contains(modifier);
    }

    @Test
    public void whenPeriodicEffectIsRemoved_thenItIsRemovedFromTheEntity() {
//        AttributeModifier modifier = new AttributeModifier()
//                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
//                .withMagnitude(100f)
//                .withOperator(Attribute.Operator.ADD);
//
//        Effect periodicEffect = new PeriodicEffect()
//                .addModifier(modifier);
//
//        e.applyEffect(periodicEffect);
//        assertThat(e.effects).contains(periodicEffect);
//
//        e.removeEffect(periodicEffect);
//
//        assertThat(e.effects).isEmpty();
//        assertThat(e.attributes.getHealth().getModifiers()).isEmpty();
    }

    @Test
    public void whenDurationEffectIsRemoved_thenItIsRemovedFromTheEntity_andItsModifiersAreRemovedFromTheAttributes() {
//        AttributeModifier modifier = new AttributeModifier()
//                .forAttribute(AttributeSet.AttributeDefinition.HEALTH)
//                .withMagnitude(100f)
//                .withOperator(Attribute.Operator.ADD);
//
//        Effect durationEffect = new DurationEffect()
//                .addModifier(modifier);
//
//        e.applyEffect(durationEffect);
//        assertThat(e.effects).contains(durationEffect);
//        assertThat(e.attributes.getHealth().getModifiers()).contains(modifier);
//
//        e.removeEffect(durationEffect);
//
//        assertThat(e.effects).isEmpty();
//        assertThat(e.attributes.getHealth().getModifiers()).isEmpty();
    }

}
