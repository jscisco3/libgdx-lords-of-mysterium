package com.jscisco.lom.dungeon;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.archetypes.ArchetypeFactory;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.components.Tile;
import com.jscisco.lom.components.model.TileActor;
import com.jscisco.lom.states.ProcessingState;
import com.jscisco.lom.states.State;
import com.jscisco.lom.systems.InitiativeSystem;
import com.jscisco.lom.systems.MovementSystem;
import com.jscisco.lom.systems.RenderSystem;
import com.jscisco.lom.util.Position3D;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dungeon extends Table {

    private static final Logger logger = LoggerFactory.getLogger(Dungeon.class);

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<Tile> mTile;
    private ComponentMapper<InitiativeComponent> mInitiative;

    private Block[][][] blocks;
    private ArchetypeFactory archetypeFactory;

    private Deque<State> states = new ArrayDeque<>();
    private World world = createWorld();

    public Dungeon(Size3D size) {
        blocks = new Block[size.getDepth()][size.getHeight()][size.getWidth()];
        world.inject(this);
        archetypeFactory = new ArchetypeFactory(world);

        for (int x = 0; x < size.getWidth(); x++) {
            for (int y = 0; y < size.getHeight(); y++) {
                for (int z = 0; z < size.getDepth(); z++) {
                    int floor = world.create(archetypeFactory.floorArchetype);
                    mPosition.get(floor).position = new Position3D(x, y, z);
                    TileActor actor = new TileActor(Assets.floor);
                    mTile.get(floor).actor = actor;
                    addActor(actor);
                }
            }
        }

        int player = world.create(archetypeFactory.playerArchetype);
        mPosition.get(player).position = new Position3D(0, 0, 0);
        TileActor actor = new TileActor(Assets.player);
        mTile.get(player).actor = actor;
        mInitiative.get(player).initiative = 1000;
        logger.info(Integer.toString(player));
        addActor(actor);


        states.add(new ProcessingState(world));
//        states.add(new PlayerTurnState(world));
    }

    private World createWorld() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new MovementSystem())
                .with(new InitiativeSystem(this))
                .with(new RenderSystem())
                .build();

        return new World(config);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public Block[][][] getBlocks() {
        return blocks;
    }

    public void popState() {
        if (states.size() > 1) {
            states.removeFirst();
        }
        logger.info("A state has been popped. Current state is: %s".format(states.toString()));
    }

    public void pushState(State state) {
        states.addFirst(state);
        logger.info("%s has been pushed and is the current state.".format(states.toString()));
    }

    public State getCurrentState() {
        return states.peekFirst();
    }

}
