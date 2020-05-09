package com.jscisco.lom.shelf.states;

import java.util.ArrayList;
import java.util.List;

public class StateManagerImpl implements StateManager {

    private List<State> states = new ArrayList<>();

    public StateManagerImpl() {
    }

    public StateManagerImpl(State state) {
        this.states.add(state);
    }

    @Override
    public void push(State state) {
        this.states.add(state);
        state.start();
    }

    @Override
    public State pop() {
        if (this.states.isEmpty()) {
            return null;
        }
        State state = this.states.remove(this.states.size() - 1);
        state.stop();
        return state;
    }

    @Override
    public void swap(State state) {
        this.pop();
        this.push(state);
    }

    @Override
    public State getCurrentState() {
        return this.states.get(this.states.size() - 1);
    }
}
