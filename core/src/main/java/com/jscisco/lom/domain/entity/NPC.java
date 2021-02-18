package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public class NPC extends Entity {

    private AIController aiController;

    @Override
    public Action nextAction() {
        return aiController.getNextAction(this);
    }
}
