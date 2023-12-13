package com.jscisco.lom.map;

// TODO: Migrate to `map` package

import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.item.Item;
import com.jscisco.lom.domain.zone.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Level {
    final Logger logger = LoggerFactory.getLogger(Level.class);
    // Representation of the level
    final int width;
    final int height;
    final int depth;

    // Contents of the level

    final Tile[][] tiles;

    private List<Item> items;
    private List<Entity> entities = new ArrayList<>();

    // Whose turn it is.
    // TODO: This should probably not be here, instead elsewhere.
    private int currentActorIndex = 0;

    public Level(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.tiles = new Tile[this.width][this.height];
    }
}
