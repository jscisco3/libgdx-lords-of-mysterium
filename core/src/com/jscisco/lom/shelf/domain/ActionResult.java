package com.jscisco.lom.shelf.domain;

import java.util.List;

public class ActionResult {
    // True if the action succeeded.
    private final boolean succeeded;
    private final boolean finished;
    private final Action alternative;
    private List<Event> events;

    public ActionResult(boolean succeeded, boolean finished, Action alternative, List<Event> events) {
        this.succeeded = succeeded;
        this.finished = finished;
        this.alternative = alternative;
        this.events = events;
    }

    public static ActionResult success(List<Event> events) {
        return new ActionResult(true, true, null, events);
    }

    public static ActionResult failure(List<Event> events) {
        return new ActionResult(false, true, null, events);
    }

    public static ActionResult alternate(Action alternative, List<Event> events) {
        return new ActionResult(true, false, alternative, events);
    }

    public boolean succeeded() {
        return succeeded;
    }

    public Action getAlternative() {
        return alternative;
    }

    public List<Event> events() {
        return events;
    }

    @Override
    public String toString() {
        return "ActionResult{" +
                "succeeded=" + succeeded +
                ", finished=" + finished +
                ", alternative=" + alternative +
                '}';
    }
}
