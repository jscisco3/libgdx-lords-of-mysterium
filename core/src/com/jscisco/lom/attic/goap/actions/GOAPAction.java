package com.jscisco.lom.attic.goap.actions;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;

import java.util.HashMap;
import java.util.Map;

public abstract class GOAPAction {

    private Map<GOAPGoal, Object> preconditions;
    private Map<GOAPGoal, Object> effects;
    private int cost;
    protected Position target;
    private boolean done;
    private boolean inRange;

    protected GOAPAction() {
        this.preconditions = new HashMap<>();
        this.effects = new HashMap<>();
        this.cost = 1;
        this.target = null;
        this.done = false;
        this.inRange = false;
    }

    protected void addPrecondition(GOAPGoal key, Object value) {
        this.preconditions.put(key, value);
    }

    protected void addEffect(GOAPGoal goal, Object value) {
        this.effects.put(goal, value);
    }

    protected void setCost(int cost) {
        this.cost = cost;
    }

    public void doReset() {
        inRange = false;
        target = null;
        reset();
    }

    /**
     * Resets any variables that need to be reset before planning happens again.
     */
    public abstract void reset();

    /**
     * Procedurally check if this action can run. Not all actions will need it, but some might.
     *
     * @param entity The entity that this action is affecting
     * @return true if the procedural preconditions are met
     */
    public abstract boolean checkProceduralPreconditions(Entity entity);

    /**
     * This is responsible for setting the Entity's nextAction
     *
     * @param entity The entity that this action is affecting
     * @return true if we can perform the action
     */
    public abstract boolean perform(Entity entity);

    public Map<GOAPGoal, Object> getPreconditions() {
        return preconditions;
    }

    public Map<GOAPGoal, Object> getEffects() {
        return effects;
    }

    public int getCost() {
        return cost;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

    void finish() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    /*
      Does this action need to be within range of a target position?
      If not, then MoveTo state will not run for this action (e.g. drop item here)
      Else, MoveTo will tell the entity to move until within range

      @return
     */
//    public abstract boolean requiresInRange();

    /**
     * Determines if we are in range of the target.
     * The MoveTo state will set thios and it gets reset each time this action is performed
     *
     * @return whether or not we are in range
     */
    public boolean isInRange() {
        return inRange;
    }

    public void setInRange(boolean inRange) {
        this.inRange = inRange;
    }
}
