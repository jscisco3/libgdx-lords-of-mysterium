package com.jscisco.lom.attributes.ai;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.entity.Entity;

public class RestAI implements AI {

    @Override
    public Action nextAction(Entity entity) {
        return new RestAction(entity);
    }
}
