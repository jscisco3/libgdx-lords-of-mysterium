package com.jscisco.lom.shelf.effect;

import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.combat.DamageType;
import com.jscisco.lom.shelf.entity.Entity;
import com.jscisco.lom.shelf.domain.Health;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDamageEffect {

    Entity entity = new Entity() {
    };

    @Test
    void damageEffectShouldReduceHealthOfEntityItIsAppliedTo() {
        this.entity.setHealth(new Health(100));
        DamageEffect effect = new DamageEffect(new Damage(DamageType.PHYSICAL, 10, 10));
        effect.apply(this.entity);
        Assertions.assertThat(this.entity.getHealth().hp()).isEqualTo(90);
    }

}
