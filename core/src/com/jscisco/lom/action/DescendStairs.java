package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DescendStairs extends AbstractAction {

    private static final Logger logger = LoggerFactory.getLogger(DescendStairs.class);

    public DescendStairs(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
        assert source != null;

        if (source.getPosition().equals(source.getStage().getPositionOfStairsDown())) {
            logger.info("trying to descend stairs");
            return ActionResult.success();
        }
        return ActionResult.failure();
    }
}
