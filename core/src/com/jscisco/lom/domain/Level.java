package com.jscisco.lom.domain;

import java.util.List;

/**
 * A Level is the representation of a single part of a Zone.
 * It contains everything that needs to be "known" for that particular space - all game objects and terrain.
 */
public class Level {
    /**
     * All game objects that are characters
     */
    private List<GameObject> entities;

    /**
     * All game objects that are items
     */
    private List<GameObject> items;

}
