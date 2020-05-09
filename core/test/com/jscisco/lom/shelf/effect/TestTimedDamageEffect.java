package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.combat.DamageType;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.domain.Health;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class TestTimedDamageEffect {

    @Spy
    Entity entity = new Entity() {};

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void damageEffectShouldDealDamageToAttachedEntity() {
        this.entity.setHealth(new Health(100));
        TimedDamageEffect tde = new TimedDamageEffect(2, new DamageEffect(new Damage(DamageType.PHYSICAL, 20, 20)));
        tde.apply(this.entity);
        tde.tick();
        Assertions.assertThat(this.entity.getHealth().hp()).isEqualTo(80);
        tde.tick();
        Assertions.assertThat(this.entity.getHealth().hp()).isEqualTo(60);
        Assertions.assertThat(this.entity.getEffects().isEmpty()).isTrue();
    }

}
