package com.jscisco.lom.dungeon;

import com.jscisco.lom.LOMGame;
import com.jscisco.lom.actor.Actor;
import com.jscisco.lom.actor.Player;
import com.jscisco.lom.repositories.TerrainRepository;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.states.State;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.util.Position3D;
import com.jscisco.lom.util.Size3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidgrid.FOV;
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

    private Player player;

    private FOV fovCalculator = new FOV();

    public Dungeon(Size3D size) {
        this.size = size;
        blocks = new Block[size.getWidth()][size.getHeight()][size.getDepth()];

        generateDungeon();

        player = new Player(findEmptyPositionZLevel(0));
        player.getFieldOfView().initialize(this, player);

        states.add(new PlayerTurnState(this));
    }

    public Position3D findEmptyPositionZLevel(int z) {
        while (true) {
            int x = LOMGame.rng.between(0, this.size.getWidth());
            int y = LOMGame.rng.between(0, this.size.getHeight());
            if (this.blocks[x][y][z].getTerrain().isWalkable()) {
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


    private void generateDungeon() {
        char[][] map;
        Terrain[][] terrainMap = new Terrain[size.getWidth()][size.getHeight()];

        DungeonGenerator generator = new DungeonGenerator(size.getWidth(), size.getHeight());
        for (int z = 0; z < size.getDepth(); z++) {
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
                        blocks[x][y][z] = new Block(TerrainRepository.WALL);
                    } else {
                        blocks[x][y][z] = new Block(TerrainRepository.FLOOR);
                    }
                }
            }
        }

    }

    public int getHeight() {
        return size.getHeight();
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getDepth() {
        return size.getDepth();
    }

    public boolean terrainIsWalkableAtPosition(Position3D position3D) {
        return blocks[position3D.getX()][position3D.getY()][position3D.getZ()].getTerrain().isWalkable();
    }

    public Terrain getTerrainAtPosition(Position3D position3D) {
        return blocks[position3D.getX()][position3D.getY()][position3D.getZ()].getTerrain();
    }

    public Terrain getTerrainAtPosition(int x, int y, int z) {
        return blocks[x][y][z].getTerrain();
    }

    public void updateBlocksBasedOnFOV() {
        player.getFieldOfView().calculateFov(player, fovCalculator);
        double[][] playerFov = player.getFieldOfView().getFov();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (playerFov[x][y] > 0.0) {
                    blocks[x][y][player.getZ()].setSeen(true);
                    blocks[x][y][player.getZ()].setInFov(true);
                } else {
                    blocks[x][y][player.getZ()].setInFov(false);
                }
            }
        }
    }
}
