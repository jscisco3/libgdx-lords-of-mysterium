package com.jscisco.lom.entities;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.entity.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPlayer {

    private Player player;

    @Test
    void shouldGetDefaultAttackActionIfNoEquipment() {
        player = new Player.Builder("player")
                .build();

        Attack attack = player.getAttack();
        Assertions.assertThat(attack.getDamages()).isNotEmpty();
    }
}
