package com.jscisco.lom.domain.state;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.entity.Hero;

import java.util.Set;

public abstract class State {

    protected final Hero hero;
    protected Action action;

    public State(Hero hero) {
        this.hero = hero;
    }

    public Action getNextAction() {
        Action a = this.action;
        this.action = null;
        return a;
    }

    public abstract void handleInput(Set<Integer> input);

    @Override
    public String toString() {
        return "State";
    }
}
