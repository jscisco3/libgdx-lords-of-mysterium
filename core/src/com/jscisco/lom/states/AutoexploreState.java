package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.commands.MoveCommand;
import com.jscisco.lom.dungeon.Dungeon;
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


    public AutoexploreState(Dungeon dungeon) {
        super(dungeon);
    }

    @Override
    public void update() {
        // Calculate explore map
        DijkstraMap dijkstraMap = new DijkstraMap();
        dijkstraMap.initialize(calculateAutoexploreCosts());
        Coord playerCoord = Coord.get(dungeon.getPlayer().getPosition().getX(), dungeon.getPlayer().getPosition().getY());

        Coord[] goals = new Coord[1];
        goals[0] = Coord.get(95, 1);

        List<Coord> path = dijkstraMap.findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                playerCoord,
                goals);

        if (!path.isEmpty() && !path.get(0).equals(playerCoord)) {
            Command command = new MoveCommand(dungeon, dungeon.getPlayer(),
                    path.get(0).x - playerCoord.x,
                    path.get(0).y - playerCoord.y,
                    0);
            command.invoke();
        } else {
            dungeon.popState();
        }
    }

    @Override
    public Command handleInput(Input input) {
        if (input.isKeyPressed(Input.Keys.ESCAPE)) {
            dungeon.popState();
        }
        return null;
    }

    private double[][] calculateAutoexploreCosts() {
        double[][] costs = new double[dungeon.getWidth()][dungeon.getHeight()];
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                Terrain t = dungeon.getTerrainAtPosition(x, y, 0);
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
}
