package com.jscisco.lom.domain.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler {

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    public void handle(Event event) {
        logger.info("Handling event: " + event);
    }

}
