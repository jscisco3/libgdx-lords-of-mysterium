package com.jscisco.lom.ability;

import com.jscisco.lom.combat.Damage;
import com.jscisco.lom.combat.DamageType;
import com.jscisco.lom.effect.DamageEffect;
import com.jscisco.lom.effect.Effect;
import com.jscisco.lom.effect.TimedDamageEffect;
import com.jscisco.lom.entity.EntityName;
import com.jscisco.lom.entity.Health;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import squidpony.squidai.AOE;
import squidpony.squidai.BeamAOE;
import squidpony.squidai.BlastAOE;
import squidpony.squidai.PointAOE;
import squidpony.squidgrid.Radius;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

public class TestAbility {

    Ability ability;
    Stage stage = new StageImpl(20, 20);

    @Test
    public void whenAStageAbilityIsCreatedItShouldGetConvertedToASquidlibMap() {
        AOE aoe = Mockito.spy(new BeamAOE(Coord.get(1, 1), Coord.get(6, 6)));

        ability = new Ability(0, new ArrayList<>(), stage, aoe);
        Mockito.verify(aoe).setMap(any());
    }

    @Test
    public void shouldReturnTheCorrectAreaForPointAOE() {
        AOE aoe = new PointAOE(Coord.get(5, 5));
        ability = new Ability(0, new ArrayList<>(), stage, aoe);

        Assertions.assertThat(ability.area().get(Coord.get(5, 5))).isEqualTo(1.0);
    }

    @Test
    public void shouldReturnTheCorrectAreaForBlastAOE() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        ability = new Ability(0, new ArrayList<>(), stage, aoe);

        Assertions.assertThat(ability.area().get(Coord.get(5, 5))).isEqualTo(1.0);

        Set<Coord> affectedCoords = ability.area().keySet();
        for (Map.Entry<Coord, Double> entry : ability.area().entrySet()) {
            System.out.println(entry);
        }
        System.out.println(ability.area().entrySet().size());
        for (Coord c : affectedCoords) {
//            System.out.println(c);
        }
    }

    @Test
    void stageAbilityShouldAffectAllEntitiesInAffectedArea() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        List<Effect> effects = new ArrayList<>();
        effects.add(new DamageEffect(new Damage(DamageType.FIRE, 10)));
        ability = new Ability(0, effects, stage, aoe);

        NPC npc = new NPC.Builder(new EntityName("Snuugz"))
                .withStage(stage)
                .withPosition(Position.get(5, 5))
                .withHealth(new Health(50))
                .build();

        ability.applyEffects();
        Assertions.assertThat(npc.getHealth().getHp()).isEqualTo(40);
    }

    @Test
    void stageAbilityCanApplyTimedEffects() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        List<Effect> effects = new ArrayList<>();
        TimedDamageEffect tde = new TimedDamageEffect(1, new DamageEffect(new Damage(DamageType.FIRE, 20)));
        effects.add(tde);
//        effects.add(new DamageEffect(new Damage(DamageType.FIRE, 10)));
        ability = new Ability(0, effects, stage, aoe);

        NPC npc = new NPC.Builder(new EntityName("Snuugz"))
                .withStage(stage)
                .withPosition(Position.get(5, 5))
                .withHealth(new Health(50))
                .build();

        ability.applyEffects();
        Assertions.assertThat(npc.getEffects().isEmpty()).isFalse();
        Assertions.assertThat(npc.getEffects().get(0)).isEqualTo(tde);
        tde.tick();
        Assertions.assertThat(npc.getHealth().getHp()).isEqualTo(30);
        Assertions.assertThat(npc.getEffects().isEmpty()).isTrue();
    }
}