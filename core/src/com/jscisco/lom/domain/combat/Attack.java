package com.jscisco.lom.domain.combat;

import javax.annotation.Nonnull;

public class Attack {
    @Nonnull
    final Accuracy accuracy;
    @Nonnull
    final DamageRange damageRange;

    private Attack(@Nonnull Accuracy accuracy, @Nonnull DamageRange damageRange) {
        if (accuracy == null) {
            throw new IllegalArgumentException("Accuracy cannot be null");
        }
        if (damageRange == null) {
            throw new IllegalArgumentException("Damage Range cannot be null");
        }
        this.accuracy = accuracy;
        this.damageRange = damageRange;
    }

    public static Attack of(@Nonnull Accuracy accuracy, @Nonnull DamageRange damageRange) {
        return new Attack(accuracy, damageRange);
    }

    public Damage getDamage() {
        return this.damageRange.getDamage();
    }

}
