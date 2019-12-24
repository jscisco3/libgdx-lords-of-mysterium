package com.jscisco.lom.ability;

import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.StageImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import squidpony.squidai.AOE;
import squidpony.squidai.BeamAOE;
import squidpony.squidmath.Coord;

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
}