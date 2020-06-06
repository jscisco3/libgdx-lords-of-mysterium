package com.jscisco.lom.application;

import com.jscisco.lom.domain.combat.Damage;
import com.jscisco.lom.domain.EntityFactory;
import com.jscisco.lom.domain.GameObject;
import com.jscisco.lom.domain.event.DamageEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageSystemTest {

    DamageSystem system = new DamageSystem();
    GameObject testNpc;

    @BeforeEach
    public void setUp() {
        testNpc = EntityFactory.npc();
    }

    @Test
    public void damageSystemCanHandleDamageEvent() {
        DamageEvent event = new DamageEvent(new Damage(10, Damage.Type.PHYSICAL), testNpc);
        system.handle(event);

        assertThat(testNpc.getHealth().getHp()).isLessThan(100);
    }

}
