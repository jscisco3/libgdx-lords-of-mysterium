package com.jscisco.lom.domain;

import java.util.Collections;
import java.util.List;

public class Tile {

    Feature feature = FeatureFactory.FLOOR;
    Actor occupant = null;
    List<Item> items = Collections.emptyList();

    public boolean isWalkable(Actor actor) {
        return feature.isWalkable(actor);
    }

}
