package com.jscisco.lom.attributes.ai.goap;

import com.jscisco.lom.entity.Entity;

import java.util.Stack;

public class FSM {

    private Stack<FSMState> states;

    public FSM() {
        this.states = new Stack<>();
    }

    public FSM(Stack<FSMState> states) {
        this.states = states;
    }

    public void update(Entity entity) {
        if (states.peek() != null) {
            states.peek().update(this, entity);
        }
    }

    public void push(FSMState state) {
        this.states.push(state);
    }

    public void pop() {
        if (!this.states.empty()) {
            this.states.pop();
        }
    }
}
