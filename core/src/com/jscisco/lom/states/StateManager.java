package com.jscisco.lom.states;

public interface StateManager {

    void push(State state);

    State pop();

    void swap(State state);

    State getCurrentState();

}
