package com.jscisco.lom.ability;

import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import squidpony.squidai.AOE;
import squidpony.squidmath.Coord;
import squidpony.squidmath.OrderedMap;

public class StageAbility extends Ability {
    private Stage stage;
    private AOE aoe;

    public StageAbility(Stage stage, AOE aoe) {
        this.stage = stage;
        this.aoe = aoe;
        this.aoe.setMap(this.stage.toSquidlibMap());
    }

    public OrderedMap<Coord, Double> area() {
        return null;
    }

    public Stage getStage() {
        return stage;
    }

    public AOE getAoe() {
        return aoe;
    }
}
