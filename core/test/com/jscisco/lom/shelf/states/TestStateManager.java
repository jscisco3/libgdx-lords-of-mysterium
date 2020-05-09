package com.jscisco.lom.shelf.states;

import com.badlogic.gdx.Input;
import com.jscisco.lom.shelf.LOMGame_Deprecated;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TestStateManager {

    private class TestState extends State {

        public TestState() {
            super(Mockito.mock(LOMGame_Deprecated.class));
        }

        public TestState(LOMGame_Deprecated game) {
            super(game);
        }

        @Override
        public void update() {

        }

        @Override
        public void handleInput(Input input) {

        }

        @Override
        public void start() {
        }

        @Override
        public void stop() {

        }
    }

    private StateManager stateManager;

    @BeforeEach
    void setUp() {
        this.stateManager = new StateManagerImpl();
    }

    @Test
    void test_push_state_should_put_state_as_current_state() {
        State state = new TestState(Mockito.mock(LOMGame_Deprecated.class));
        this.stateManager.push(state);
        Assertions.assertThat(this.stateManager.getCurrentState()).isEqualTo(state);
    }

    @Test
    void test_push_state_twice_should_put_second_state_as_current_state() {
        State state1 = new TestState();
        State state2 = new TestState();
        this.stateManager.push(state1);
        Assertions.assertThat(this.stateManager.getCurrentState()).isEqualTo(state1);
        this.stateManager.push(state2);
        Assertions.assertThat(this.stateManager.getCurrentState()).isEqualTo(state2);
        Assertions.assertThat(this.stateManager.getCurrentState()).isNotEqualTo(state1);
    }

    @Test
    void test_pop_state_with_no_states_returns_null() {
        Assertions.assertThat(this.stateManager.pop()).isNull();
    }

    @Test
    void test_pop_state_with_one_state_returns_that_state() {
        State state = new TestState();
        this.stateManager.push(state);
        Assertions.assertThat(this.stateManager.pop()).isEqualTo(state);
    }

    @Test
    void test_swap_state_should_change_the_current_state() {
        State state1 = new TestState();
        State state2 = new TestState();
        this.stateManager.push(state1);
        Assertions.assertThat(this.stateManager.getCurrentState()).isEqualTo(state1);
        this.stateManager.swap(state2);
        Assertions.assertThat(this.stateManager.getCurrentState()).isEqualTo(state2);
    }

}
