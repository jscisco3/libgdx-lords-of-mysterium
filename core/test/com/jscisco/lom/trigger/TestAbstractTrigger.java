package com.jscisco.lom.trigger;

import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.effect.DamageEffect;
import com.jscisco.lom.effect.Effect;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.domain.Health;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAbstractTrigger {

    private Entity source;
    private Entity receiver;

    @BeforeEach
    void setUp() {
        source = new Entity() {
        };
        receiver = new Entity() {
        };
        source.setHealth(new Health(100));
        receiver.setHealth(new Health(100));
    }

    @Test
    void testTriggerWithSourceEffects() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> sourceEffects = new ArrayList<>();
        sourceEffects.add(damageEffect);
        AbstractTrigger trigger = new AbstractTrigger(sourceEffects, new ArrayList<>()) {
        };

        trigger.trigger(source, receiver, null);
        Assertions.assertThat(source.getHealth().hp()).isEqualTo(50);
        Assertions.assertThat(receiver.getHealth().hp()).isEqualTo(100);

    }

    @Test
    void testTriggerWithReceiverEffects() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> receiverEffects = new ArrayList<>();
        receiverEffects.add(damageEffect);
        AbstractTrigger trigger = new AbstractTrigger(new ArrayList<>(), receiverEffects) {
        };

        trigger.trigger(source, receiver, null);
        Assertions.assertThat(source.getHealth().hp()).isEqualTo(100);
        Assertions.assertThat(receiver.getHealth().hp()).isEqualTo(50);
    }

    @Test
    void testTriggerWithSourceAndReceiverEffects() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> sourceEffects = new ArrayList<>();
        sourceEffects.add(damageEffect);
        AbstractTrigger trigger = new AbstractTrigger(sourceEffects, new ArrayList<>(sourceEffects)) {
        };

        trigger.trigger(source, receiver, null);

        Assertions.assertThat(source.getHealth().hp()).isEqualTo(50);
        Assertions.assertThat(receiver.getHealth().hp()).isEqualTo(50);
    }

    @Test
    void testTriggerWithNullSourceAndSourceEffects() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> sourceEffects = new ArrayList<>();
        sourceEffects.add(damageEffect);
        AbstractTrigger trigger = new AbstractTrigger(sourceEffects, new ArrayList<>()) {
        };

        trigger.trigger(null, receiver, null);
        Assertions.assertThat(source.getHealth().hp()).isEqualTo(100);
        Assertions.assertThat(receiver.getHealth().hp()).isEqualTo(100);
    }

    @Test
    void testTriggerWithNullReceiverAndReceiverEffects() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> receiverEffects = new ArrayList<>();
        receiverEffects.add(damageEffect);
        AbstractTrigger trigger = new AbstractTrigger(new ArrayList<>(), receiverEffects) {
        };

        trigger.trigger(null, null, null);
        Assertions.assertThat(source.getHealth().hp()).isEqualTo(100);
        Assertions.assertThat(receiver.getHealth().hp()).isEqualTo(100);

    }

}
