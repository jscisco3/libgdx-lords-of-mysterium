package com.jscisco.lom.dungeon;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.components.model.TileActor;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dungeon extends Table {

    private static final Logger logger = LoggerFactory.getLogger(Dungeon.class);

    private World world;
    private Block[][][] blocks;

    public Dungeon(Size3D size) {
        blocks = new Block[size.getDepth()][size.getHeight()][size.getWidth()];
        WorldConfiguration config = new WorldConfigurationBuilder()
                .build();
        world = new World(config);

        TileActor floorTile = new TileActor(Assets.floor);
        floorTile.setWidth(24);
        floorTile.setHeight(24);
        floorTile.setPosition(48, 48);
        addActor(floorTile);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Block[][][] getBlocks() {
        return blocks;
    }
}
