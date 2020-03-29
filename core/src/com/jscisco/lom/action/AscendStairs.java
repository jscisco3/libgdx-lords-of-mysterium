package com.jscisco.lom.action;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.domain.Position;

import java.util.Optional;

public class AscendStairs extends AbstractAction {

    public AscendStairs(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
        assert source != null;

        Optional<Position> stairsUp = source.getStage().getStairsUpPosition();

        if (stairsUp.isPresent() && stairsUp.get().equals(source.getPosition())) {
            return ActionResult.success();
        }
        return ActionResult.failure();
    }
}
