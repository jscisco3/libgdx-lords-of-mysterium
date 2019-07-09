package com.jscisco.lom.action;

public class ActionResult {

    private boolean succeeded;
    private Action alternative;

    public ActionResult(boolean succeeded, Action alternative) {
        this.succeeded = succeeded;
        this.alternative = alternative;
    }

    public static ActionResult success() {
        return new ActionResult(true, null);
    }

    public static ActionResult failure() {
        return new ActionResult(false, null);
    }

    public static ActionResult alternate(Action alternative) {
        return new ActionResult(false, alternative);
    }

    public boolean succeeded() {
        return succeeded;
    }
}
