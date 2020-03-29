package com.jscisco.lom.action;

import com.jscisco.lom.combat.Attack;
import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.domain.Health;
import com.jscisco.lom.entity.*;
import com.jscisco.lom.zone.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

@Disabled
public class TestAttackAction {

    @Spy
    private Entity attacker;
    private Entity defender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.attacker = new Player.Builder(new EntityName("attacker"))
                .withHealth(new Health(100))
                .build();

        this.defender = new NPC.Builder(new EntityName("defender"))
                .withHealth(new Health(100))
                .build();


        List<Attack> attacks = new ArrayList<>();
        attacks.add(new Attack(10, new Damage(DamageType.PHYSICAL, 20, 40)));
        Mockito.doReturn(attacks).when(attacker).getAttacks();
    }

    @Test
    public void attackActionShouldDealDamage() {
        new AttackAction(attacker, defender).invoke();
        Assertions.assertThat(defender.getHealth().hp()).isLessThan(100);
    }

    @Test
    public void attackActionShouldRemoveDefenderFromStageIfDestroyed() {
        Stage stage = new Stage();

        this.attacker = new Player.Builder(new EntityName("attacker"))
                .build();
        this.defender = new NPC.Builder(new EntityName("defender"))
                .withHealth(new Health(1))
                .withStage(stage)
                .build();

        Assertions.assertThat(stage.getEntities()).isNotEmpty();
        new AttackAction(attacker, defender).invoke();
        Assertions.assertThat(stage.getEntities()).isEmpty();
    }

}
