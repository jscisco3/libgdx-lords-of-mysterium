package com.jscisco.lom.ai.goap;

import com.jscisco.lom.attributes.ai.goap.GOAPPlanner;
import com.jscisco.lom.attributes.ai.goap.GOAPPlannerImpl;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.entity.Entity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TestGOAPPlannerImpl {

    public GOAPPlanner planner;

    @BeforeEach
    public void setUp() {
        this.planner = new GOAPPlannerImpl();
    }

    @Test
    public void oneGoalShouldBeEasy() {
        Map<String, Object> worldState = new HashMap<>();
        worldState.put("hasThis", true);
        worldState.put("notHaveThis", false);

        Map<String, Object> goal = new HashMap<>();
        goal.put("complete", true);

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
    public void oneGoalWithOneActionThatAchievesThatGoalReturnsAValidPlan() {
        Map<String, Object> worldState = new HashMap<>();
        worldState.put("hasThis", true);
        worldState.put("hasThis", true);

        Map<String, Object> goal = new HashMap<>();
        goal.put("hasThis", true);
    }

    @Test
    public void oneGoalWithNoAvailableActionsReturnsNoPlan() {
        Map<String, Object> worldState = new HashMap<>();
        worldState.put("hasThis", true);
        worldState.put("notHaveThis", false);

        Map<String, Object> goal = new HashMap<>();
        goal.put("hasThis", true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue.remove()).isInstanceOf(TestGOAPActionOne.class);
    }

    @Test
    public void oneGoalWithChainedActionsReturnsTheExpectedPlan() {
        Map<String, Object> worldState = new HashMap<>();
        worldState.put("hasThis", true);
        worldState.put("notHaveThis", false);

        Map<String, Object> goal = new HashMap<>();
        goal.put("complete", true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());
        availableActions.add(new TestGOAPActionTwo());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue.remove()).isInstanceOf(TestGOAPActionOne.class);
        Assertions.assertThat(queue.remove()).isInstanceOf(TestGOAPActionTwo.class);
    }

    @Test
    public void oneGoalWithMultiplePossibilitesReturnsCheapestPlan() {
        Map<String, Object> worldState = new HashMap<>();
        worldState.put("hasThis", true);
        worldState.put("notHaveThis", false);

        Map<String, Object> goal = new HashMap<>();
        goal.put("complete", true);

        Set<GOAPAction> availableActions = new HashSet<>();
        availableActions.add(new TestGOAPActionOne());
        availableActions.add(new TestGOAPActionTwo());
        availableActions.add(new TestGOAPActionThree());

        Queue<GOAPAction> queue = planner.plan(new TestEntity(), availableActions, worldState, goal);

        Assertions.assertThat(queue.peek()).isInstanceOf(TestGOAPActionThree.class);
    }

    private class TestGOAPActionOne extends GOAPAction {

        public TestGOAPActionOne() {
            super();
            // We need this
            addPrecondition("hasThis", true);
            // We don't have this
            addPrecondition("notHaveThis", false);
            // We now have that
            addEffect("notHaveThis", true);
            setCost(1);
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
        public TestEntity() {
            super(null);
        }
    }

    private class TestGOAPActionTwo extends GOAPAction {

        public TestGOAPActionTwo() {
            super();
            setCost(5);
            // We need this
            // We don't have this
            addPrecondition("notHaveThis", true);
            // We now have that
            addEffect("complete", true);
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

        public TestGOAPActionThree() {
            super();
            setCost(4);
            // We need this
            // We don't have this
            addPrecondition("hasThis", true);
            // We now have that
            addEffect("complete", true);
        }

        @Override
        public boolean checkProceduralPreconditions(Entity entity) {
            return true;
        }

        public boolean perform(Entity entity) {
            return true;
        }
    }

}
