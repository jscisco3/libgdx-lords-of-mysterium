package com.jscisco.lom.domain.entity;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.AStarSearch;
import squidpony.squidmath.Coord;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@javax.persistence.Entity
public class WanderAIController extends AIController {

    private static final Logger logger = LoggerFactory.getLogger(WanderAIController.class);
    @Transient
    private DijkstraMap dijkstraMap;
    @Transient
    private Coord goal;
    @Transient
    List<Coord> path;
    @Transient
    AStarSearch aStarSearch;

    public WanderAIController() {
    }

    public WanderAIController(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        Direction d = Direction.values()[GameConfiguration.random.nextInt(Direction.values().length)];
        return new WalkAction(entity, d);
    }
//
//    @Override
//    public Action getNextAction() {
////        // Either we have no goal, or we reached it.
//        if (goal == null || entity.getPosition().toCoord().equals(goal)) {
//            pickGoal();
//        }
//        Direction d = pickDirection();
//        // TODO: Try to calculate the gradient map, then determine which direction to move it based on the lowest value surrounding tiles
//        return new WalkAction(entity, d);
//
////        if (path.isEmpty() || (path.size() == 1 && path.get(0).equals(entity.getPosition().toCoord()))) {
////            logger.info(MessageFormat.format("Path from {0} to {1} is empty, so recalculating path", entity.getPosition(), this.goal));
//////            calculatePathAStar();
////            calculatePathDijkstra();
////        }
////        logger.info("" + entity.level.getCurrentActorIndex());
////        Position p = entity.getPosition();
////        // TODO: How to handle when we do not move?
////        // If we do not move, then next == our position, thus, we should remove that one and get the next coord in the path
////        Coord next = path.remove(0);
////        if (next.equals(entity.getPosition().toCoord())) {
////            path.remove(0);
////            next = path.get(0);
////        }
////        logger.info(MessageFormat.format("Picking direction from position {0} to coord {1}", p, next));
////        Direction d = Direction.byValue(Position.fromCoord(next).subtract(p));
////        return new WalkAction(entity, d);
//    }

    private void pickGoal() {
        if (dijkstraMap == null) {
            initializeDijkstraMap(entity);
        }
        if (aStarSearch == null) {
            initializeAStarSearch();
        }
        // Pick a random, walkable tile
        logger.trace("Choosing goal...");
        this.goal = entity.getLevel().getEmptyTile(entity).toCoord();
        this.dijkstraMap.setGoal(this.goal);
        this.dijkstraMap.scan(entity.position.toCoord(), null);
        logger.info(MessageFormat.format("Goal chosen {0}", this.goal));
//        calculatePathDijkstra();
//        calculatePathAStar();
    }

    private Direction pickDirection() {
        Direction d = Direction.N;
        double cost = Double.MAX_VALUE;
        for (Position p : relativePositions()) {
            if (this.dijkstraMap.gradientMap[p.getX()][p.getY()] < cost) {
                cost = this.dijkstraMap.gradientMap[p.getX()][p.getY()];
                d = Direction.byValue(p.subtract(entity.position));
            }
        }
        return d;
    }

    private List<Position> relativePositions() {
        Position p = entity.getPosition();
        return Arrays.asList(
                p.add(Direction.N.relativePosition),
                p.add(Direction.NE.relativePosition),
                p.add(Direction.NW.relativePosition),
                p.add(Direction.E.relativePosition),
                p.add(Direction.W.relativePosition),
                p.add(Direction.SW.relativePosition),
                p.add(Direction.S.relativePosition),
                p.add(Direction.SE.relativePosition)
        );
    }

    private void calculatePathDijkstra() {
        this.path = this.dijkstraMap.findPath(10, null, null, entity.getPosition().toCoord(), this.goal);
        logger.info(String.valueOf(this.path.get(0)));
    }

    private void calculatePathAStar() {
        this.path = this.aStarSearch.path(entity.position.toCoord(), this.goal);
        this.path.remove(0);
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

    private void initializeAStarSearch() {
        Level level = entity.level;
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = level.getTileAt(Position.of(x, y)).isWalkable(entity) ? 1.0 : DijkstraMap.WALL;
            }
        }
        aStarSearch = new AStarSearch(weights, AStarSearch.SearchType.CHEBYSHEV);
    }
}
