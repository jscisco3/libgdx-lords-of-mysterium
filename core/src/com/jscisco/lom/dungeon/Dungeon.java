package com.jscisco.lom.dungeon;

import com.artemis.World;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dungeon {

    private static final Logger logger = LoggerFactory.getLogger(Dungeon.class);

    private World world;
    private Block[][][] blocks;

    public Dungeon(Size3D size) {
        blocks = new Block[size.getDepth()][size.getHeight()][size.getWidth()];
    }

    public Block[][][] getBlocks() {
        return blocks;
    }
}
