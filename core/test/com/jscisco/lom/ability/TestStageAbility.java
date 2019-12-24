package com.jscisco.lom.ability;

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

import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

public class TestStageAbility {

    StageAbility ability;
    Stage stage = new StageImpl(20, 20);

    @Test
    public void whenAStageAbilityIsCreatedItShouldGetConvertedToASquidlibMap() {
        AOE aoe = Mockito.spy(new BeamAOE(Coord.get(1, 1), Coord.get(6, 6)));

        ability = new StageAbility(stage, aoe);
        Mockito.verify(aoe).setMap(any());
    }

    @Test
    public void shouldReturnTheCorrectAreaForPointAOE() {
        AOE aoe = new PointAOE(Coord.get(5, 5));
        ability = new StageAbility(stage, aoe);

        Assertions.assertThat(ability.area().get(Coord.get(5, 5))).isEqualTo(1.0);
    }

    @Test
    public void shouldReturnTheCorrectAreaForBlastAOE() {
        AOE aoe = new BlastAOE(Coord.get(5, 5), 2, Radius.SQUARE);
        ability = new StageAbility(stage, aoe);

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
}