package com.jscisco.lom.combat;

import java.util.ArrayList;
import java.util.List;

public final class UnarmedAttackFactory {

    public static List<Attack> getUnarmedAttacks() {
        List<Attack> attacks = new ArrayList<>();
        attacks.add(new Attack(
                100,
                new Damage(DamageType.PHYSICAL, 5, 10)
        ));
        return attacks;
    }

}
