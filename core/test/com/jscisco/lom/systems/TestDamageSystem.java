package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.DamageComponent;
import com.jscisco.lom.components.HealthComponent;
import com.jscisco.lom.components.flags.Destroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDamageSystem {

    private ComponentMapper<DamageComponent> mDamage;
    private ComponentMapper<HealthComponent> mHealth;
    private ComponentMapper<Destroy> mDestroy;

    private World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new DamageSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void damage_system_should_reduce_health_correctly() {
        int e = world.create();
        HealthComponent hc = mHealth.create(e);
        hc.maxHp = 100;
        hc.hp = 100;

        DamageComponent dc = mDamage.create(e);
        dc.damage = 50;

        world.process();

        Assertions.assertThat(hc.hp).isEqualTo(50);
        Assertions.assertThat(mDamage.has(e)).isFalse();
    }

    @Test
    public void if_health_0_or_less_add_destroy() {
        int e = world.create();
        HealthComponent hc = mHealth.create(e);
        hc.maxHp = 100;
        hc.hp = 100;

        DamageComponent dc = mDamage.create(e);
        dc.damage = 100;

        world.process();

        Assertions.assertThat(mDestroy.has(e)).isTrue();
    }

}
