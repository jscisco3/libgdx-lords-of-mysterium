package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;

public class AscendStairs extends AbstractAction {

    public AscendStairs(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
        assert source != null;
        if (source.getPosition().equals(source.getStage().getPositionOfStairsUp())) {
            return ActionResult.success();
        }
        // Fail when trying to ascend stairs that don't exist
        return ActionResult.failure();
    }
}
