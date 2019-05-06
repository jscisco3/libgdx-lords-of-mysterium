package com.jscisco.lom.dungeon;

import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.actor.Player;
import com.jscisco.lom.archetypes.ArchetypeFactory;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.components.model.TileActor;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.states.State;
import com.jscisco.lom.util.Position3D;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;
import squidpony.squidmath.RNG;
import squidpony.squidmath.StatefulRNG;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dungeon {

    // TODO: Configuration file
    private float DEFAULT_TILE_WIDTH = 24.0f;
    private float DEFAULT_TILE_HEIGHT = 24.0f;

    private static final Logger logger = LoggerFactory.getLogger(Dungeon.class);

    private Size3D size;
    private Block[][][] blocks;
    private ArchetypeFactory archetypeFactory;

    private Deque<State> states = new ArrayDeque<>();

    private Actor player;

    private float[][][] resistanceMap;
    private char[][] floor;

    public Dungeon(Size3D size) {
        this.size = size;
        blocks = new Block[size.getDepth()][size.getHeight()][size.getWidth()];

        resistanceMap = new float[size.getDepth()][size.getHeight()][size.getWidth()];

        this.floor = generateFloor();
        calculateResistanceMap();

        Actor player = new Player(new Position3D(10, 10, 0));
        TileActor actor = new TileActor(Assets.player);
//        actor.setPosition(player.getPosition().getX() * DEFAULT_TILE_WIDTH, player.getPosition().getY() * DEFAULT_TILE_HEIGHT);
        this.player = player;


        states.add(new PlayerTurnState(this));
    }

    public Block[][][] getBlocks() {
        return blocks;
    }

    public void popState() {
        if (states.size() > 1) {
            states.removeFirst();
        }
    }

    public void pushState(State state) {
        states.addFirst(state);
    }

    public State getCurrentState() {
        return states.peekFirst();
    }


    public Actor getPlayer() {
        return player;
    }


    private char[][] generateFloor() {
        char[][] map;

        DungeonGenerator generator = new DungeonGenerator(size.getWidth(), size.getHeight());
        RNG rng = new StatefulRNG();
        generator.addDoors(25, false);
        SerpentMapGenerator serpent = new SerpentMapGenerator(size.getWidth(), size.getHeight(), rng, 0.2);
        serpent.putWalledBoxRoomCarvers(2);
        serpent.putWalledRoundRoomCarvers(2);
        serpent.putCaveCarvers(2);
        map = serpent.generate();
        DungeonUtility.closeDoors(generator.generate(map));
        return map;
    }

    public void calculateResistanceMap() {
        for (int z = 0; z < size.getDepth(); z++) {
            for (int y = 0; y < size.getHeight(); y++) {
                for (int x = 0; x < size.getWidth(); x++) {
                }
            }
        }
    }

    public char[][] getFloor() {
        return floor;
    }

    public int getHeight() {
        return size.getHeight();
    }

    public int getWidth() {
        return size.getWidth();
    }
}
