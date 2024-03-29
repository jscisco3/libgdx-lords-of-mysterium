package com.jscisco.lom.ai;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.map.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.AStarSearch;
import squidpony.squidmath.Coord;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class WanderAIController extends AIController {

    private static final Logger logger = LoggerFactory.getLogger(WanderAIController.class);
    private DijkstraMap dijkstraMap;
    private Coord goal;
    List<Coord> path;
    AStarSearch aStarSearch;

    public WanderAIController() {
    }

    public WanderAIController(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        // Either we have no goal, or we reached it.
        if (goal == null || entity.getPosition().toCoord().equals(goal)) {
            pickGoal();
        }
        Position p = entity.getPosition();
        // TODO: How to handle when we do not move?
        // If we do not move, then next == our position, thus, we should remove that one and get the next coord in the
        // path
//        this.path = this.dijkstraMap.findPath(1, null, null, entity.getPosition().toCoord(), this.goal);
        calculatePathDijkstra();
        Coord next = this.path.getFirst();
        logger.info("Picking direction from position {} to coord {}", p, next);
        Direction d = Direction.byValue(Position.fromCoord(next).subtract(p));
        return new WalkAction(entity, d);
    }

    private void pickGoal() {
        if (dijkstraMap == null) {
            initializeDijkstraMap(entity);
        }
        if (aStarSearch == null) {
            initializeAStarSearch();
        }
        // Pick a random, walkable tile
        logger.info("Choosing goal for {}", entity.getName());
        this.goal = GameConfiguration.rng.getRandomElement(entity.getLevel().getWalkableTiles(entity)).toCoord();
        this.dijkstraMap.setGoal(this.goal);
        // this.dijkstraMap.scan(entity.position.toCoord(), null);
        logger.info("Goal chosen: {}", this.goal);
        calculatePathDijkstra();
        calculatePathAStar();
    }

    private Direction pickDirection() {
        Direction d = Direction.N;
        double cost = Double.MAX_VALUE;
        for (Position p : relativePositions()) {
            if (this.dijkstraMap.gradientMap[p.getX()][p.getY()] < cost) {
                cost = this.dijkstraMap.gradientMap[p.getX()][p.getY()];
                d = Direction.byValue(p.subtract(entity.getPosition()));
            }
        }
        return d;
    }

    private List<Position> relativePositions() {
        Position p = entity.getPosition();
        return Arrays.asList(p.add(Direction.N.relativePosition), p.add(Direction.NE.relativePosition),
                p.add(Direction.NW.relativePosition), p.add(Direction.E.relativePosition),
                p.add(Direction.W.relativePosition), p.add(Direction.SW.relativePosition),
                p.add(Direction.S.relativePosition), p.add(Direction.SE.relativePosition));
    }

    private void calculatePathDijkstra() {
        this.path = this.dijkstraMap.findPath(1, null, null, entity.getPosition().toCoord(), this.goal);
    }

    private void calculatePathAStar() {
        this.path = this.aStarSearch.path(entity.getPosition().toCoord(), this.goal);
        this.path.removeFirst();
    }

    private void initializeDijkstraMap(Entity entity) {
        Level level = entity.getLevel();
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = level.getTile(Position.of(x, y)).isWalkable(entity) ? DijkstraMap.FLOOR
                        : DijkstraMap.WALL;
            }
        }
        this.dijkstraMap = new DijkstraMap(weights, Measurement.EUCLIDEAN);
    }

    private void initializeAStarSearch() {
        Level level = entity.getLevel();
        double[][] weights = new double[level.getWidth()][level.getHeight()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = level.getTile(Position.of(x, y)).isWalkable(entity) ? 1.0 : DijkstraMap.WALL;
            }
        }
        aStarSearch = new AStarSearch(weights, AStarSearch.SearchType.CHEBYSHEV);
    }
}
