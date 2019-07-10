package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.dungeon.Block;
import com.jscisco.lom.dungeon.Zone;
import com.jscisco.lom.terrain.Floor;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.terrain.Wall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class AutoexploreState extends State {

    private Logger logger = LoggerFactory.getLogger(AutoexploreState.class);

    public AutoexploreState(Zone zone) {
        super(zone);
    }

    @Override
    public void update() {
        // Calculate explore map
        DijkstraMap dijkstraMap = new DijkstraMap();
        dijkstraMap.initialize(calculateAutoexploreCosts());
        Coord playerCoord = Coord.get(zone.getPlayer().getPosition().getX(), zone.getPlayer().getPosition().getY());

        List<Coord> goalList = getCoordsOfUnseenBlocks(zone.getPlayer().getZ());
        Coord[] goals = getCoordsOfUnseenBlocks(zone.getPlayer().getZ()).toArray(new Coord[goalList.size()]);

        List<Coord> path = dijkstraMap.findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                playerCoord,
                goals);

        if (!path.isEmpty() && !path.get(0).equals(playerCoord)) {
            Action action = new MoveAction(zone.getPlayer(),
                    path.get(0).x - playerCoord.x,
                    path.get(0).y - playerCoord.y,
                    0);
            zone.getPlayer().setNextAction(action);
        } else {
            zone.popState();
        }
    }

    @Override
    public void handleInput(Input input) {
        if (input.isKeyPressed(Input.Keys.ESCAPE)) {
            zone.popState();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    private double[][] calculateAutoexploreCosts() {
        double[][] costs = new double[zone.getWidth()][zone.getHeight()];
        for (int x = 0; x < zone.getWidth(); x++) {
            for (int y = 0; y < zone.getHeight(); y++) {
                Terrain t = zone.getTerrainAtPosition(x, y, 0);
                if (t.getClass() == Floor.class) {
                    costs[x][y] = DijkstraMap.FLOOR;
                }
                if (t.getClass() == Wall.class) {
                    costs[x][y] = DijkstraMap.WALL;
                }
            }
        }
        return costs;
    }

    private List<Coord> getCoordsOfUnseenBlocks(int z) {
        List<Coord> goals = new ArrayList<>();
        Block[][] blocks = zone.getBlocksByZLevel(z);
        for (int x = 0; x < zone.getWidth(); x++) {
            for (int y = 0; y < zone.getHeight(); y++) {
                if (!blocks[x][y].isSeen()) {
                    goals.add(Coord.get(x, y));
                }
            }
        }
        return goals;
    }
}
