package com.jscisco.lom.ability;

import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.effect.DamageEffect;
import com.jscisco.lom.effect.TimedDamageEffect;
import com.jscisco.lom.entity.EntityName;
import com.jscisco.lom.domain.Health;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.zone.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import squidpony.squidai.AOE;
import squidpony.squidai.BlastAOE;
import squidpony.squidgrid.Radius;
import squidpony.squidmath.Coord;

public class TestAbility {

    Ability ability;
    Stage stage = new Stage(20, 20);

    @Test
    void stageAbilityShouldAffectAllEntitiesInAffectedArea() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        ability = new Ability.Builder()
                .withName(new AbilityName("Test Ability"))
                .withCooldown(0)
                .withEffect(new DamageEffect(new Damage(DamageType.FIRE, 10, 10)))
                .withAOE(aoe)
                .build();

        NPC npc = new NPC.Builder(new EntityName("Snuugz"))
                .withStage(stage)
                .withPosition(Position.get(5, 5))
                .withHealth(new Health(50))
                .build();

        ability.applyEffects(stage);
        Assertions.assertThat(npc.getHealth().hp()).isEqualTo(40);
    }

    @Test
    void stageAbilityCanApplyTimedEffects() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        TimedDamageEffect tde = new TimedDamageEffect(1, new DamageEffect(new Damage(DamageType.FIRE, 20, 20)));
        ability = new Ability.Builder()
                .withName(AbilityName.of("Test Ability"))
                .withCooldown(0)
                .withEffect(tde)
                .withAOE(aoe)
                .build();

        NPC npc = new NPC.Builder(new EntityName("Snuugz"))
                .withStage(stage)
                .withPosition(Position.get(5, 5))
                .withHealth(new Health(50))
                .build();

        ability.applyEffects(stage);
        Assertions.assertThat(npc.getEffects().isEmpty()).isFalse();
        Assertions.assertThat(npc.getEffects().get(0)).isEqualTo(tde);
        tde.tick();
        Assertions.assertThat(npc.getHealth().hp()).isEqualTo(30);
        Assertions.assertThat(npc.getEffects().isEmpty()).isTrue();
    }
}