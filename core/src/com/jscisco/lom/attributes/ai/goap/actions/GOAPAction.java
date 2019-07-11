package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.util.Position;

import java.util.HashMap;
import java.util.Map;

public abstract class GOAPAction {

    private Map<GOAPGoal, Object> preconditions;
    private Map<GOAPGoal, Object> effects;
    private int cost;
    private Position target;

    public GOAPAction() {
        this.preconditions = new HashMap<>();
        this.effects = new HashMap<>();
        this.cost = 1;
        this.target = null;
    }

    public void addPrecondition(GOAPGoal key, Object value) {
        this.preconditions.put(key, value);
    }

    public void addEffect(GOAPGoal goal, Object value) {
        this.effects.put(goal, value);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void reset() {

    }

    public boolean checkProceduralPreconditions(Entity entity) {
        return true;
    }

    /**
     * This is responsible for setting the Entity's nextAction
     *
     * @param entity
     * @return
     */
    public boolean perform(Entity entity) {
        return true;
    }

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
}
