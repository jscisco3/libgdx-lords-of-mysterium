package com.jscisco.lom.action;

public class ActionResult {

    // True if the action succeeded.
    private boolean succeeded;
    private boolean finished;
    private Action alternative;

    public ActionResult(boolean succeeded, boolean finished, Action alternative) {
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

    public boolean succeeded() {
        return succeeded;
    }

    public Action getAlternative() {
        return alternative;
    }
}
