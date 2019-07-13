package com.jscisco.lom.ai.goap;

import com.jscisco.lom.attic.goap.GOAPPlanner;
import com.jscisco.lom.attic.goap.GOAPPlannerImpl;
import com.jscisco.lom.attic.goap.actions.GOAPAction;
import com.jscisco.lom.attic.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.Entity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class TestGOAPPlannerImpl {

    private GOAPPlanner planner;

    @BeforeEach
    void setUp() {
        this.planner = new GOAPPlannerImpl();
    }

    @Test
    void oneGoalShouldBeEasy() {
        Map<GOAPGoal, Object> worldState = new HashMap<>();
        worldState.put(GOAPGoal.HAS_TARGET, true);
        worldState.put(GOAPGoal.TARGET_DESTROYED, false);

        Map<GOAPGoal, Object> goal = new HashMap<>();
        goal.put(GOAPGoal.COMPLETE, true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());
        availableActions.add(new TestGOAPActionTwo());
        availableActions.add(new TestGOAPActionThree());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        while (queue.peek() != null) {
            System.out.println(queue.remove());
        }
    }

    @Test
    void oneGoalWithOneActionThatAchievesThatGoalReturnsAValidPlan() {
        Map<GOAPGoal, Object> worldState = new HashMap<>();
        worldState.put(GOAPGoal.HAS_TARGET, true);

        Map<GOAPGoal, Object> goal = new HashMap<>();
        goal.put(GOAPGoal.HAS_TARGET, true);
    }

    @Test
    void oneGoalWithNoAvailableActionsReturnsNoPlan() {
        Map<GOAPGoal, Object> worldState = new HashMap<>();
        worldState.put(GOAPGoal.HAS_TARGET, true);
        worldState.put(GOAPGoal.TARGET_DESTROYED, false);

        Map<GOAPGoal, Object> goal = new HashMap<>();
        goal.put(GOAPGoal.COMPLETE, true);

        Set<GOAPAction> availableActions = new HashSet<>();

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue).isNull();
    }

    @Test
    void oneGoalWithChainedActionsReturnsTheExpectedPlan() {
        Map<GOAPGoal, Object> worldState = new HashMap<>();
        worldState.put(GOAPGoal.HAS_TARGET, true);
        worldState.put(GOAPGoal.TARGET_DESTROYED, false);

        Map<GOAPGoal, Object> goal = new HashMap<>();
        goal.put(GOAPGoal.COMPLETE, true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());
        availableActions.add(new TestGOAPActionTwo());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue.remove()).isInstanceOf(TestGOAPActionOne.class);
        Assertions.assertThat(queue.remove()).isInstanceOf(TestGOAPActionTwo.class);
    }

    @Test
    void oneGoalWithMultiplePossibilitesReturnsCheapestPlan() {
        Map<GOAPGoal, Object> worldState = new HashMap<>();
        worldState.put(GOAPGoal.HAS_TARGET, true);
        worldState.put(GOAPGoal.TARGET_DESTROYED, false);

        Map<GOAPGoal, Object> goal = new HashMap<>();
        goal.put(GOAPGoal.COMPLETE, true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());
        availableActions.add(new TestGOAPActionTwo());
        availableActions.add(new TestGOAPActionThree());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue.peek()).isInstanceOf(TestGOAPActionThree.class);
    }

    private class TestGOAPActionOne extends GOAPAction {

        TestGOAPActionOne() {
            super();
            // We need this
            addPrecondition(GOAPGoal.HAS_TARGET, true);
            // We don't have this
            addPrecondition(GOAPGoal.TARGET_DESTROYED, false);
            // We now have that
            addEffect(GOAPGoal.TARGET_DESTROYED, true);
            setCost(1);
        }

        @Override
        public void reset() {

        }

        @Override
        public boolean checkProceduralPreconditions(Entity entity) {
            return true;
        }

        public boolean perform(Entity entity) {
            return true;
        }
    }

    private class TestEntity extends Entity {
        TestEntity() {
            super(null);
        }
    }

    private class TestGOAPActionTwo extends GOAPAction {

        TestGOAPActionTwo() {
            super();
            setCost(5);
            // We need this
            // We don't have this
            addPrecondition(GOAPGoal.TARGET_DESTROYED, true);
            // We now have that
            addEffect(GOAPGoal.COMPLETE, true);
        }

        @Override
        public void reset() {

        }

        @Override
        public boolean checkProceduralPreconditions(Entity entity) {
            return true;
        }

        public boolean perform(Entity entity) {
            return true;
        }
    }

    private class TestGOAPActionThree extends GOAPAction {

        TestGOAPActionThree() {
            super();
            setCost(4);
            // We need this
            // We don't have this
            addPrecondition(GOAPGoal.HAS_TARGET, true);
            // We now have that
            addEffect(GOAPGoal.COMPLETE, true);
        }

        @Override
        public boolean checkProceduralPreconditions(Entity entity) {
            return true;
        }

        @Override
        public void reset() {

        }

        public boolean perform(Entity entity) {
            return true;
        }
    }

}
