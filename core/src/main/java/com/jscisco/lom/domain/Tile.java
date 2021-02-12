package com.jscisco.lom.domain;

import java.util.Collections;
import java.util.List;

public class Tile {

    private Feature feature = FeatureFactory.FLOOR;
    private Actor occupant = null;
    private List<Item> items = Collections.emptyList();

}
