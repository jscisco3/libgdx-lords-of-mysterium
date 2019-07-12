package com.jscisco.lom.attic;

import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.attributes.ai.AbstractAI;
import com.jscisco.lom.entity.Entity;

public class RestAI extends AbstractAI {

    public RestAI(Entity entity) {
        super(entity);
    }

    @Override
    public Action nextAction() {
        return new RestAction(this.entity);
    }
}
