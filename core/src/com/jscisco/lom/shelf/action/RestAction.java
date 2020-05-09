package com.jscisco.lom.shelf.action;

import com.jscisco.lom.shelf.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestAction extends AbstractAction {

    private static final Logger logger = LoggerFactory.getLogger(RestAction.class);

    public RestAction(Entity source) {
        super(source);
    }

    @Override
    public ActionResult invoke() {
        logger.debug("{} is resting.", source);
        return ActionResult.success();
    }
}
