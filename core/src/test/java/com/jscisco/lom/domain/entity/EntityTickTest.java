package com.jscisco.lom.domain.entity;

import shelf.attribute.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityTickTest extends BaseEntityTest {

    @Test
    public void whenAnEntityTicks_thenItsEffectsTick() {
        AttributeModifier modifier = new AttributeModifier()
                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                .withMagnitude(100f)
                .withOperator(Attribute.Operator.ADD);

        Effect periodicEffect = new PeriodicEffect()
                .withDuration(Duration.turns(20))
                .addModifier(modifier);

        e.applyEffect(periodicEffect);
        e.tick();

        assertThat(e.effects).contains(periodicEffect);
        assertThat(e.attributes.getMaxHealth().getValue()).isEqualTo(200f);

        e.tick();
        assertThat(e.attributes.getMaxHealth().getValue()).isEqualTo(300f);
    }

    @Test
    public void whenAnEffectExpiresFromTicking_itIsRemoved() {
        AttributeModifier modifier = new AttributeModifier()
                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                .withMagnitude(100f)
                .withOperator(Attribute.Operator.ADD);

        Effect periodicEffect = new PeriodicEffect()
                .withDuration(Duration.turns(1))
                .addModifier(modifier);

        e.applyEffect(periodicEffect);
        assertThat(e.effects).contains(periodicEffect);

        e.tick();
        assertThat(e.effects).isEmpty();
    }

    @Test
    public void whenDurationEffectExpires_ItsModifiersAreRemovedAppropriately() {
        AttributeModifier modifier = new AttributeModifier()
                .forAttribute(AttributeSet.AttributeDefinition.MAX_HEALTH)
                .withMagnitude(100f)
                .withOperator(Attribute.Operator.ADD);

        Effect durationEffect = new DurationEffect()
                .withDuration(Duration.turns(1))
                .addModifier(modifier);

        e.applyEffect(durationEffect);
        assertThat(e.effects).contains(durationEffect);
        assertThat(e.attributes.getMaxHealth().getModifiers()).contains(modifier);

        e.tick();
        assertThat(e.effects).isEmpty();
        assertThat(e.attributes.getMaxHealth().getModifiers()).isEmpty();
    }

}
