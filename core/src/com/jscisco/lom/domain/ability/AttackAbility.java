package com.jscisco.lom.domain.ability;

import com.jscisco.lom.domain.Entity;

import java.util.Objects;

/**
 *
 */
public class AttackAbility extends Ability {

    private final Entity attacker;
    private final Entity defender;

    private AttackAbility(Entity attacker, Entity defender) {
        super(AbilityName.of("Attack"),
                AbilityDescription.of("A basic melee attack"));
        Objects.requireNonNull(attacker, "Attacker must not be null");
        Objects.requireNonNull(defender, "Defender must not be null");
        this.attacker = attacker;
        this.defender = defender;
    }

    public static AttackAbility of(Entity attacker, Entity defender) {
        return new AttackAbility(attacker, defender);
    }

    @Override
    public void invoke() {

    }
}
