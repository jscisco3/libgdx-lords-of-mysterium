package com.jscisco.lom.domain.state;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.entity.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public abstract class State {

    private static final Logger logger = LoggerFactory.getLogger(State.class);
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
