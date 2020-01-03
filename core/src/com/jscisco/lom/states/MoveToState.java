package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import com.jscisco.lom.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.ArrayList;
import java.util.List;

public class MoveToState extends State {

    private Position goal;

    private static final Logger logger = LoggerFactory.getLogger(MoveToState.class);

    public MoveToState(Zone zone, Position goal) {
        super(zone);
        // TODO: Update this goal to be nearest pathable tile?
        this.goal = goal;
        zone.getCurrentStage().getPlayer().getPathingMap().reset();
    }

    @Override
    public void update() {
        Stage stage = zone.getCurrentStage();
        Coord playerCoord = Coord.get(stage.getPlayer().getPosition().getX(), stage.getPlayer().getPosition().getY());

        List<Coord> path = stage.getPlayer().getPathingMap().findPath(1,
                new ArrayList<Coord>(),
                new ArrayList<Coord>(),
                playerCoord,
                Coord.get(goal.getX(), goal.getY()));

        if (path.isEmpty()) {
            logger.info("No path found from {} to {}", stage.getPlayer().getPosition(), goal);
            zone.popState();
        }

        if (!path.isEmpty() && !path.get(0).equals(playerCoord)) {
            logger.info("Next step: {}", path.get(0));
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
}
