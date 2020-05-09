package com.jscisco.lom.shelf.trigger;

import com.jscisco.lom.shelf.combat.Damage;
import com.jscisco.lom.shelf.combat.DamageType;
import com.jscisco.lom.shelf.effect.DamageEffect;
import com.jscisco.lom.shelf.effect.Effect;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class TestWhenDamagedTrigger {

    WhenDamagedTrigger thorns;

    @BeforeEach
    void setUp() {
        DamageEffect damageEffect = new DamageEffect(new Damage(DamageType.PHYSICAL, 50, 50));
        List<Effect> sourceEffects = new ArrayList<>();
        sourceEffects.add(damageEffect);
        thorns = new WhenDamagedTrigger(sourceEffects, new ArrayList<>());
    }
}
