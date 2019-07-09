package com.jscisco.lom.action;

import com.jscisco.lom.actor.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestAction extends Action {

    private static final Logger logger = LoggerFactory.getLogger(RestAction.class);

    public RestAction(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
//        logger.info("{} is resting.", source);
        return ActionResult.success();
    }
}
