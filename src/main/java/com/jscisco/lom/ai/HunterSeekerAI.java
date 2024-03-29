package com.jscisco.lom.ai;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.RestAction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Entity;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.entity.NPC;
import com.jscisco.lom.map.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidai.DijkstraMap;
import squidpony.squidgrid.Measurement;
import squidpony.squidmath.Coord;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

public class HunterSeekerAI extends AIController {

    private static final Logger logger = LoggerFactory.getLogger(HunterSeekerAI.class);
    private DijkstraMap dijkstraMap;
    private Entity target; // Allow the AI to target things other than the player

    public HunterSeekerAI() {
    }

    public HunterSeekerAI(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        if (target == null || target.getPools().getHp().getCurrent() <= 0) {
            if (!chooseTarget(entity)) {
                // Could not choose a target.
                logger.info("Could not find a target, no hero?");
                ((NPC) entity).setAiController(new WanderAIController(entity));
            }
        }
        // Get path to the target
        if (this.dijkstraMap == null) {
            initializeDijkstraMap(entity);
        }
        logger.debug("Calculating path from {} to {}", entity.getPosition(), target.getPosition());
        List<Coord> path = this.dijkstraMap.findPath(1, null, null, entity.getPosition().toCoord(),
                this.target.getPosition().toCoord());
        if (path.isEmpty()) {
            logger.debug(MessageFormat.format("We have a target at position: {0} but we could not find a path from {1}",
                    target.getPosition(), entity.getPosition()));
            return new RestAction(entity);
        }
        Direction d = Direction.byValue(Position.fromCoord(path.getFirst()).subtract(entity.getPosition()));
        return new WalkAction(entity, d);
    }

    private boolean chooseTarget(Entity entity) {
        Optional<Entity> target = entity.getLevel().getEntities().stream().filter(e -> e instanceof Hero).findFirst();
        if (target.isPresent()) {
            this.target = target.get();
            return true;
        }
        return false;
    }

    private void initializeDijkstraMap(Entity entity) {
        Level level = entity.getLevel();
        double[][] weights = new double[level.getWidth()][level.getHeight()];
        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                weights[x][y] = level.getTile(Position.of(x, y)).isWalkable(entity) ? DijkstraMap.FLOOR
                        : DijkstraMap.WALL;
            }
        }
        this.dijkstraMap = new DijkstraMap(weights, Measurement.EUCLIDEAN);
    }
}
