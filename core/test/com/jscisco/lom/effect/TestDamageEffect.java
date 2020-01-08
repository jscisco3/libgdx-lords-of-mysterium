package com.jscisco.lom.effect;

import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.Health;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDamageEffect {

    Entity entity = new Entity() {
    };

    @Test
    void damageEffectShouldReduceHealthOfEntityItIsAppliedTo() {
        this.entity.setHealth(new Health(100));
        DamageEffect effect = new DamageEffect(new Damage(DamageType.PHYSICAL, 10));
        effect.apply(this.entity);
        Assertions.assertThat(this.entity.getHealth().getHp()).isEqualTo(90);
    }

}
