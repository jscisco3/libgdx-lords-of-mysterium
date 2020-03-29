package com.jscisco.lom.domain;

import com.jscisco.lom.application.EventProcessor;

public abstract class Action {

    EventProcessor eventProcessor;
    Entity source;

    public Action(EventProcessor eventProcessor, Entity source) {
        if (eventProcessor == null) {
            throw new IllegalArgumentException("EventProcessor cannot be null!");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source entity cannot be null!");
        }
        this.eventProcessor = eventProcessor;
        this.source = source;
    }

    public ActionResult invoke() {
        return ActionResult.success();
    }

}
