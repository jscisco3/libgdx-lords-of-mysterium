package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.RestAction;

/**
 * An AI controller primarily used for testing. Just has the controlled entity "sleep"
 */
public class RestAIController extends AIController {

    public RestAIController() {
    }

    public RestAIController(Entity entity) {
        super(entity);
    }

    @Override
    public Action getNextAction() {
        return new RestAction(entity);
    }
}
