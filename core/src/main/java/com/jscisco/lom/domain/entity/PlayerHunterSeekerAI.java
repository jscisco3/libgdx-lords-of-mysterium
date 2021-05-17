package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.RestAction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.state.DefaultState;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.Coord;

import javax.persistence.Transient;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

public class PlayerHunterSeekerAI extends AIController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerHunterSeekerAI.class);
    @Transient
    private DijkstraMap dijkstraMap;
    @Transient
    private Entity target; // Allow the AI to target things other than the player

    public PlayerHunterSeekerAI(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        if (target == null || !entity.level.getEntities().contains(target)) {
            logger.info("Choosing new target...");
            if (!chooseTarget(entity)) {
                // Could not choose a target.
                logger.info("Could not find a target, no NPCs?");
                ((Hero) entity).setState(new DefaultState((Hero) entity));
                return new RestAction(entity);
            }
        }
        // Get path to the target
        if (this.dijkstraMap == null) {
            initializeDijkstraMap(entity);
        }
        List<Coord> path = this.dijkstraMap.findPath(1, null, null, entity.getPosition().toCoord(), this.target.getPosition().toCoord());
        if (path.isEmpty()) {
            logger.info(MessageFormat.format("We have a target at position: {0} but we could not find a path from {1}", target.getPosition(), entity.getPosition()));
            return new RestAction(entity);
        }
        Direction d = Direction.byValue(Position.fromCoord(path.get(0)).subtract(entity.getPosition()));
        return new WalkAction(entity, d);
    }

    private boolean chooseTarget(Entity entity) {
        Optional<Entity> target = entity.getLevel().getEntities().stream().filter(e -> e instanceof NPC).findFirst();
        if (target.isPresent()) {
            this.target = target.get();
            logger.info("Player found target, hunting now!");
            return true;
        }
        return false;
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
