package com.jscisco.lom.domain.action;

public class ActionResult {

    private final Action alternative;
    private final boolean success;

    private ActionResult(Action alternative, boolean success) {
        this.alternative = alternative;
        this.success = success;
    }

    public static ActionResult succeeded() {
        return new ActionResult(null, true);
    }

    public static ActionResult failed() {
        return new ActionResult(null, false);
    }

    public static ActionResult alternate(Action alternative) {
        return new ActionResult(alternative, true);
    }

    public boolean success() {
        return this.success;
    }

    public boolean hasAlternate() {
        return this.alternative != null;
    }

    public Action getAlternative() {
        return alternative;
    }
}
