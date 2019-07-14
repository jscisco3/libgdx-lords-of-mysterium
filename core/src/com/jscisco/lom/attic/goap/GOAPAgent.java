package com.jscisco.lom.attic.goap;

import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.attic.goap.actions.GOAPAction;
import com.jscisco.lom.attic.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class GOAPAgent {

    private static final Logger logger = LoggerFactory.getLogger(GOAPAgent.class);

    private FSM stateMachine;

    private FSMState idleState; // Find something to do
    private FSMState performActionState; // Perform an action

    private Set<GOAPAction> availableActions;
    private Queue<GOAPAction> currentActions;

    private GOAPPlanner planner;

    // The entity which is being controlled by this agent
    private Entity entity;

    public GOAPAgent(Entity entity) {
        this.entity = entity;
        this.stateMachine = new FSM();
        this.availableActions = new HashSet<>();
        this.currentActions = new ArrayDeque<>();
        this.planner = new GOAPPlannerImpl();

        createIdleState();
        createPerformActionState();

        stateMachine.push(idleState);
        loadActions();
    }

    public void update() {
        this.stateMachine.update(this.entity);
    }

    private boolean hasActionPlan() {
        return !currentActions.isEmpty();
    }

    private void createIdleState() {

        idleState = (fsm, entity) -> {
            logger.debug("Updating IDLE state");
            Map<GOAPGoal, Object> worldState = entity.getWorldState();
            Map<GOAPGoal, Object> goal = entity.getGoals();

            logger.debug("Available Actions: {}", availableActions);
            logger.debug("World State: {} ; Goal: {}", worldState, goal);
            Queue<GOAPAction> plan = planner.plan(entity, availableActions, worldState, goal);
            logger.info("Plan: {}", plan);
            if (plan != null) {
                currentActions = plan;
                // Push PerformAction state to top of queue
                fsm.push(performActionState);
            } else {
                // We don't know what to do, so just rest.
                entity.setNextAction(new RestAction(entity));
            }
        };
    }

    private void createPerformActionState() {
        performActionState = (fsm, entity) -> {
            logger.debug("Updating PERFORM ACTION state");
            if (!hasActionPlan()) {
                logger.debug("Done actions");
                fsm.pop(); // Pop down to idle state
                return;
            }

            GOAPAction action = currentActions.peek();

            assert action != null;

            logger.debug("{} is done? {}", action, action.isDone());
            if (action.isDone()) {
                // If this action is done, remove it from the current actions so we can do the next one.
                currentActions.remove();
            }

            if (hasActionPlan()) {
                action = currentActions.peek();
                logger.debug("Action we are about to perform: {}", action);
                if (action != null) {
                    boolean success = action.perform(entity);
                    if (!success) {
                        fsm.pop(); // We didn't succeed so we go back to IdleState
                    }
                }
            } else {
                // We do not have a plan, so we pop this state (ActionState) and push IdleState
                fsm.pop();
            }
        };
    }

    private void loadActions() {
//        this.availableActions = ((NPC) entity).getAvailableActions();
    }

}
