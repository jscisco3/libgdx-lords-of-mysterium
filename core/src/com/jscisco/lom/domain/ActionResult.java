package com.jscisco.lom.domain;

public class ActionResult {
    final boolean succeeded;
    final boolean finished;
    final Action alternative;

    private ActionResult(boolean succeeded, boolean finished, Action alternative) {
        this.succeeded = succeeded;
        this.finished = finished;
        this.alternative = alternative;
    }

    public static ActionResult success() {
        return new ActionResult(true, true, null);
    }

    public static ActionResult failure() {
        return new ActionResult(false, true, null);
    }

    public static ActionResult alternate(Action alternative) {
        return new ActionResult(true, false, alternative);
    }
}
