package com.jscisco.lom.action;

import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.entity.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAttackAction {

    private Entity attacker;
    private Entity defender;

    @Test
    public void attackActionShouldDealDamage() {
        this.attacker = new Player.Builder("attacker")
                .withHealth(new Health(100))
                .build();

        this.defender = new NPC.Builder("defender")
                .withHealth(new Health(100))
                .build();

        new AttackAction(attacker, defender).invoke();
        Assertions.assertThat(defender.getHealth().getHp()).isLessThan(100);
    }

}
