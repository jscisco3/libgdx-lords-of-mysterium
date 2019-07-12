package com.jscisco.lom.attributes.ai.goap;

import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.action.RestAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.Entity;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.util.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import squidpony.squidmath.Coord;

import java.util.*;

public class GOAPAgent {

    private static final Logger logger = LoggerFactory.getLogger(GOAPAgent.class);

    private FSM stateMachine;

    private FSMState idleState; // Find something to do
    private FSMState moveToState; // Move to a target
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
        createMoveToState();
        createPerformActionState();

        stateMachine.push(idleState);
        loadActions();
    }

    public void update() {
        this.stateMachine.update(this.entity);
    }

    public void addAction(GOAPAction action) {
        this.addAction(action);
    }

    private boolean hasActionPlan() {
        return !currentActions.isEmpty();
    }

    private void createIdleState() {

        idleState = new FSMState() {
            @Override
            public void update(FSM fsm, Entity entity) {
                logger.info("Updating IDLE state");
                Map<GOAPGoal, Object> worldState = entity.getWorldState();
                Map<GOAPGoal, Object> goal = entity.getGoals();

                Queue<GOAPAction> plan = planner.plan(entity, availableActions, worldState, goal);
                logger.info("Available Actions: {}", availableActions);
                logger.info("World State: {} ; Goal: {}", worldState, goal);
                logger.info("Plan: {}", plan);
                if (plan != null) {
                    currentActions = plan;
                    // Move to PerformAction state
                    fsm.pop();
                    fsm.push(performActionState);
                } else {
                    // We don't know what to do, so just rest.
                    entity.setNextAction(new RestAction(entity));
                }
            }
        };
    }

    private void createMoveToState() {
        moveToState = new FSMState() {
            @Override
            public void update(FSM fsm, Entity entity) {
                logger.info("Updating MOVE TO state");
                GOAPAction action = currentActions.peek();
                if (action.requiresInRange() && action.getTarget() == null) {
                    logger.debug("{} requires a target, but has none. Plan failed.", action);
                    fsm.pop(); // Move
                    fsm.pop(); // perform
                    fsm.push(idleState);
                    return;
                }
                // Get the agent to move itself
                Position direction = calculateDirectionToWalk(action.getTarget());
                entity.setNextAction(new MoveAction(entity, direction));
                if (calculatePathToTarget(action.getTarget(), 5).size() > 1) {
                    action.setInRange(true);
                    fsm.pop();
                }
            }

            private List<Coord> calculatePathToTarget(Position target, int length) {
                Coord entityCoord = Coord.get(entity.getX(), entity.getY());
                List<Coord> path = entity.getPathingMap().findPath(length,
                        new ArrayList<Coord>(),
                        new ArrayList<Coord>(),
                        entityCoord,
                        Coord.get(target.getX(), target.getY()));
                return path;
            }

            private Position calculateDirectionToWalk(Position goal) {
                Coord entityCoord = Coord.get(entity.getX(), entity.getY());
                List<Coord> path = calculatePathToTarget(goal, 1);
                if (!path.isEmpty() && !path.get(0).equals(entityCoord)) {
                    return new Position(path.get(0).x - entityCoord.x, path.get(0).y - entityCoord.y);
                } else {
                    return null;
                }
            }
        };
    }

    private void createPerformActionState() {
        performActionState = new FSMState() {
            @Override
            public void update(FSM fsm, Entity entity) {
                logger.info("Updating PERFORM ACTION state");
                if (!hasActionPlan()) {
                    logger.debug("Done actions");
                    fsm.pop();
                    fsm.push(idleState);
                    return;
                }

                GOAPAction action = currentActions.peek();

                logger.info("{} is done? {}", action, action.isDone());
                if (action.isDone()) {
                    // If this action is done, remove it from the current actions so we can do the next one.
                    currentActions.remove();
                }

                if (hasActionPlan()) {
                    action = currentActions.peek();
                    logger.info("Action we are about to perform: {}", action);

                    logger.info("Requires in Range: {}", action.requiresInRange());
                    boolean inRange = action.requiresInRange() ? action.isInRange() : true;
                    if (inRange) {
                        boolean success = action.perform(entity);
                        if (!success) {
                            fsm.pop();
                            fsm.push(idleState);
                        }
                    } else {
                        // We are not in range, so we must move there.
                        fsm.push(moveToState);
                    }
                } else {
                    fsm.pop();
                    fsm.push(idleState);
                }
            }
        };
    }

    private void loadActions() {
        this.availableActions = ((NPC) entity).getAvailableActions();
    }

}
