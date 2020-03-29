package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.domain.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class DescendStairs extends AbstractAction {

    private static final Logger logger = LoggerFactory.getLogger(DescendStairs.class);

    public DescendStairs(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
        assert source != null;

        logger.info("Trying to descend!");

        Optional<Position> stairsDown = source.getStage().getStairsDownPosition();
        if (stairsDown.isPresent() && stairsDown.get().equals(source.getPosition())) {
            return ActionResult.success();
        }
        return ActionResult.failure();
    }
}
