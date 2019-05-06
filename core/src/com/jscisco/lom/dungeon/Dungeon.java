package com.jscisco.lom.dungeon;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.actor.Player;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.states.State;
import com.jscisco.lom.terrain.Floor;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.terrain.Wall;
import com.jscisco.lom.util.Position3D;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidgrid.mapping.SerpentMapGenerator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Dungeon {

    // TODO: Configuration file
    private float DEFAULT_TILE_WIDTH = 24.0f;
    private float DEFAULT_TILE_HEIGHT = 24.0f;

    private static final Logger logger = LoggerFactory.getLogger(Dungeon.class);

    private Size3D size;
    private Block[][][] blocks;

    private Deque<State> states = new ArrayDeque<>();

    private Actor player;

    private float[][][] resistanceMap;
    private Terrain[][] floor;

    private Terrain FLOOR = new Floor();
    private Terrain WALL = new Wall();

    public Dungeon(Size3D size) {
        this.size = size;
        blocks = new Block[size.getDepth()][size.getHeight()][size.getWidth()];

        resistanceMap = new float[size.getDepth()][size.getHeight()][size.getWidth()];

        this.floor = generateFloor();
        calculateResistanceMap();

        Actor player = new Player(findEmptyPositionZLevel(0));
        this.player = player;


        states.add(new PlayerTurnState(this));
    }

    public Position3D findEmptyPositionZLevel(int z) {
        while (true) {
            int x = LOMGame.rng.between(0, this.size.getWidth());
            int y = LOMGame.rng.between(0, this.size.getHeight());
            if (this.floor[x][y].isWalkable()) {
                return new Position3D(x, y, z);
            }
        }
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


    private Terrain[][] generateFloor() {
        char[][] map;
        Terrain[][] terrainMap = new Terrain[size.getWidth()][size.getHeight()];

        DungeonGenerator generator = new DungeonGenerator(size.getWidth(), size.getHeight());
        generator.addDoors(25, false);
        SerpentMapGenerator serpent = new SerpentMapGenerator(size.getWidth(), size.getHeight(), LOMGame.rng, 0.2);
        serpent.putWalledBoxRoomCarvers(2);
        serpent.putWalledRoundRoomCarvers(2);
        serpent.putCaveCarvers(2);
        map = serpent.generate();
        DungeonUtility.closeDoors(generator.generate(map));

        for (int x = 0; x < size.getWidth(); x++) {
            for (int y = 0; y < size.getHeight(); y++) {
                if (map[x][y] == '#') {
                    terrainMap[x][y] = WALL;
                } else {
                    terrainMap[x][y] = FLOOR;
                }
            }
        }
        return terrainMap;
    }

    public void calculateResistanceMap() {
        for (int z = 0; z < size.getDepth(); z++) {
            for (int y = 0; y < size.getHeight(); y++) {
                for (int x = 0; x < size.getWidth(); x++) {
                }
            }
        }
    }

    public Terrain[][] getFloor() {
        return floor;
    }

    public int getHeight() {
        return size.getHeight();
    }

    public int getWidth() {
        return size.getWidth();
    }

    public boolean terrainIsWalkableAtPosition(Position3D position3D) {
        return floor[position3D.getX()][position3D.getY()].isWalkable();
    }

    public Terrain getTerrainAtPosition(Position3D position3D) {
        return floor[position3D.getX()][position3D.getY()];
    }

    public Terrain getTerrainAtPosition(int x, int y, int z) {
        return floor[x][y];
    }
}
