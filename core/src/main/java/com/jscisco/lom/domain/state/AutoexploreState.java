package com.jscisco.lom.domain.state;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.FieldOfView;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Tile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.Coord;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutoexploreState extends State {

    private static final Logger logger = LoggerFactory.getLogger(AutoexploreState.class);

    private DijkstraMap dijkstraMap;
    final Level level;
    // This is the coordinate of the unexplored tile that we are trying to get to.
    // If it is null, there are no reachable unexplored tiles, so we will exit this state.
    private Coord goal;

    public AutoexploreState(Hero hero) {
        super(hero);
        this.level = hero.getLevel();
        initializeDijkstraMap();
    }

    private void initializeDijkstraMap() {
        double[][] weights = new double[level.getWidth()][level.getWidth()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = DijkstraMap.FLOOR;
            }
        }
        this.dijkstraMap = new DijkstraMap(weights, Measurement.EUCLIDEAN);
        // Initial goal is nearest unexplored tile
        this.goal = this.dijkstraMap.findNearest(hero.getPosition().toCoord(), unexploredCoords());
    }

    @Override
    public Action getNextAction() {
        // Initialize the dijkstra map based on what we have explore
        FieldOfView fov = hero.getFieldOfView();
        double[][] weights = new double[level.getWidth()][level.getHeight()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                Tile t = level.getTileAt(Position.of(x, y));
                if (t.isExplored()) {
                    weights[x][y] = t.isWalkable(hero) ? DijkstraMap.FLOOR : DijkstraMap.WALL;
                } else {
                    weights[x][y] = DijkstraMap.FLOOR;
                }
            }
        }
        this.dijkstraMap.initialize(weights);

        // We have goal, but it has been explored. So we should get a new one
        if (unexploredCoords().isEmpty()) {
            logger.debug("no unexplored tiles, so exiting this state.");
            hero.setState(new DefaultState(hero));
            return null;
        }
        if (this.goal != null && level.getTileAt(Position.fromCoord(this.goal)).isExplored()) {
            // This was messed up with CHEBYSHEV measurement. Not sure why, but we could not find a path to the last unexplored corner of an empty square map
            this.goal = this.dijkstraMap.findNearest(hero.getPosition().toCoord(), unexploredCoords());
        }
        // We have no goal, and no unexplored coordinates
        if (this.goal == null) {
            logger.debug("Null goal");
            hero.setState(new DefaultState(hero));
            return null;
        }
        // We have a goal, we should move towards it
        logger.debug("Goal is " + goal + " which has feature: " + level.getTileAt(Position.fromCoord(goal)).getFeature());
        List<Coord> path = this.dijkstraMap.findPath(1, null, null, hero.getPosition().toCoord(), this.goal);
        // This probably isn't reachable. But now I will be experimenting with different dungeon layouts
        if (path.isEmpty()) {
            logger.debug("Empty path, bailing for now");
            hero.setState(new DefaultState(hero));
            return null;
        }
        Coord nextStep = path.get(0);
        Direction d = Direction.byValue(Position.fromCoord(nextStep).subtract(hero.getPosition()));
        return new WalkAction(hero, d);
    }

    @Override
    public void handleInput(Set<Integer> input) {
        this.hero.setState(new DefaultState(hero));
        input.clear();
    }

    /**
     * Retrieve coordinates of all unexplored tiles
     *
     * @return list of coordinates that correspond to unexplored tiles
     */
    private List<Coord> unexploredCoords() {
        return level.getUnexploredPositions().stream()
                .map(Position::toCoord)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "AutoexploreState";
    }
}
