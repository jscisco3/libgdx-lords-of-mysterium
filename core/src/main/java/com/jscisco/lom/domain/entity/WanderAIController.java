package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.RestAction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.Coord;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

@javax.persistence.Entity
public class WanderAIController extends AIController {

    private static final Logger logger = LoggerFactory.getLogger(WanderAIController.class);
    @Transient
    private DijkstraMap dijkstraMap;
    @Transient
    private Coord goal;

    public WanderAIController() {}

    public WanderAIController(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        // Either we have no goal, or we reached it.
        if (goal == null || entity.getPosition().toCoord().equals(goal)) {
            pickGoal(entity);
        }
        List<Coord> path = this.dijkstraMap.findPath(1, null, null, entity.getPosition().toCoord(), this.goal);
        if (path.isEmpty()) {
            logger.info(MessageFormat.format("Path from {0} to {1} is empty, so resting", entity.getPosition(), this.goal));
            return new RestAction(entity);
        }
        Direction d = Direction.byValue(Position.fromCoord(path.get(0)).subtract(entity.getPosition()));
        return new WalkAction(entity, d);
    }

    private void pickGoal(Entity entity) {
        if (dijkstraMap == null) {
            initializeDijkstraMap(entity);
        }
        // Pick a random, walkable tile
        logger.info("Choosing goal...");
        List<Position> walkable = entity.getLevel().walkablePositions(entity);
        this.goal = walkable.get(GameConfiguration.random.nextInt(walkable.size())).toCoord();
        logger.info("Goal chosen: " + this.goal);
    }

    private void initializeDijkstraMap(Entity entity) {
        Level level = entity.getLevel();
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = level.getTileAt(Position.of(x, y)).isWalkable(entity) ? DijkstraMap.FLOOR : DijkstraMap.WALL;
            }
        }
        this.dijkstraMap = new DijkstraMap(weights, Measurement.EUCLIDEAN);
    }
}
