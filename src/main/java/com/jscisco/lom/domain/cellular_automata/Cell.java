package com.jscisco.lom.domain.cellular_automata;

public class Cell {

    public enum State {
        DEFINITIVELY_ALIVE, // Never update, counts as alive
        ALIVE,
        DEAD,
        DEFINITIVELY_DEAD // Never update, counts as alive
    }

    private State state;

    public Cell() {
        this.state = State.DEAD;
    }

    public Cell(State state) {
        this.state = state;
    }

    public void reproduce() {
        if (this.state == State.DEAD) {
            this.state = State.ALIVE;
        }
    }

    public void die() {
        if (this.state == State.ALIVE) {
            this.state = State.DEAD;
        }
    }

    public boolean isAlive() {
        return this.state == State.ALIVE || this.state == State.DEFINITIVELY_ALIVE;
    }

}
