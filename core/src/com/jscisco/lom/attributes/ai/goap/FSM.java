package com.jscisco.lom.attributes.ai.goap;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class FSM {

    private static final Logger logger = LoggerFactory.getLogger(FSM.class);

    private Stack<FSMState> states;

    FSM() {
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

    void push(FSMState state) {
        this.states.push(state);
    }

    void pop() {
        if (!this.states.empty()) {
            this.states.pop();
        }
    }
}
