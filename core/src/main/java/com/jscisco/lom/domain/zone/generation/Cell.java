package com.jscisco.lom.domain.zone.generation;

public class Cell {
    public enum State {
        DEFINITIVELY_DEAD,
        DEAD,
        ALIVE,
        DEFINITIVELY_ALIVE
    }

    private State state;

    public Cell() {
        this.state = State.ALIVE;
    }

    public Cell(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public boolean alive() {
        return state == State.ALIVE || state == State.DEFINITIVELY_ALIVE;
    }

    @Override
    public String toString() {
        return state.name();
    }
}
