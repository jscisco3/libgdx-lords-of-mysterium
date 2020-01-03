package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.Tile;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutoexploreState extends State {

    private static final Logger logger = LoggerFactory.getLogger(AutoexploreState.class);
    private final Stage stage;

    public AutoexploreState(Zone zone) {
        super(zone);
        this.stage = zone.getCurrentStage();
        this.stage.getPlayer().getPathingMap().reset();
    }

    @Override
    public void update() {
        Coord playerCoord = Coord.get(stage.getPlayer().getPosition().getX(), stage.getPlayer().getPosition().getY());

        Coord[] goals = getGoals();

        List<Coord> path = stage.getPlayer().getPathingMap().findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                playerCoord,
                goals);

        if (path.isEmpty()) {
            Optional<Position> stairsDownPosition = stage.getStairsDownPosition();
            if (stairsDownPosition.isPresent() && stage.getPlayer().getPosition() != stairsDownPosition.get()) {
                path = stage.getPlayer().getPathingMap().findPath(1, new ArrayList<Coord>(), new ArrayList<Coord>(), playerCoord, Coord.get(stairsDownPosition.get().getX(), stairsDownPosition.get().getY()));
            }
        }

        if (!path.isEmpty() && !path.get(0).equals(playerCoord)) {
            Action action = new MoveAction(stage.getPlayer(),
                    path.get(0).x - playerCoord.x,
                    path.get(0).y - playerCoord.y);
            stage.getPlayer().setNextAction(action);
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

    private Coord[] getGoals() {
        List<Coord> goalList = getCoordsOfUnseenBlocks();
        Coord[] goals = goalList.toArray(new Coord[goalList.size()]);
        return goals;
    }

    private List<Coord> getCoordsOfUnseenBlocks() {
        List<Coord> goals = new ArrayList<>();
        Tile[][] tiles = stage.getTiles();
        for (int x = 0; x < stage.getWidth(); x++) {
            for (int y = 0; y < stage.getHeight(); y++) {
                if (!tiles[x][y].isSeen()) {
                    goals.add(Coord.get(x, y));
                }
            }
        }
        return goals;
    }
}
