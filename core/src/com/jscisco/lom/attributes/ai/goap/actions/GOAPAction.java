package com.jscisco.lom.attributes.ai.goap.actions;

import com.jscisco.lom.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public abstract class GOAPAction {

    private Map<String, Object> preconditions;
    private Map<String, Object> effects;
    private int cost;

    public GOAPAction() {
        this.preconditions = new HashMap<>();
        this.effects = new HashMap<>();
        this.cost = 1;
    }

    public void addPrecondition(String key, Object value) {
        this.preconditions.put(key, value);
    }

    public void addEffect(String key, Object value) {
        this.effects.put(key, value);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void reset() {

    }

    public boolean checkProceduralPreconditions(Entity entity) {
        return true;
    }

    public boolean perform(Entity entity) {
        return true;
    }

    public Map<String, Object> getPreconditions() {
        return preconditions;
    }

    public Map<String, Object> getEffects() {
        return effects;
    }

    public int getCost() {
        return cost;
    }
}
