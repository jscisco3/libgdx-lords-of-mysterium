package com.jscisco.lom.shelf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Represents a collection of stages. In layman's terms, this is the generic representation of a dungeon, tower, etc.
 * It contains the collection of linked stages.
 */
public class Zone {

    private static final Logger logger = LoggerFactory.getLogger(Zone.class);

    private List<Stage> stages;

    public Zone() {
    }
}
