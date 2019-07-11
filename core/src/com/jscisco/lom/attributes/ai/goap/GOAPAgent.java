package com.jscisco.lom.attributes.ai.goap;

import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.Entity;
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
                Map<GOAPGoal, Object> worldState = new HashMap<>();
                Map<GOAPGoal, Object> goal = new HashMap<>();

                Queue<GOAPAction> plan = planner.plan(entity, availableActions, worldState, goal);
                if (plan != null) {
                    currentActions = plan;
                    // Move to PerformAction state
                    fsm.pop();
                    fsm.push(performActionState);
                } else {

                }
            }
        };
    }

    private void createMoveToState() {
        moveToState = new FSMState() {
            @Override
            public void update(FSM fsm, Entity entity) {
                GOAPAction action = currentActions.peek();
                if (action.getTarget() == null) {
                    logger.debug("{} requires a target, but has none. Plan failed.", action);
                    fsm.pop(); // Move
                    fsm.pop(); // perform
                    fsm.push(idleState);
                    return;
                }
                // Get the agent to move itself
                Position direction = calculateDirectionToWalk(action.getTarget());
                entity.setNextAction(new MoveAction(entity, direction));
            }

            private Position calculateDirectionToWalk(Position goal) {
                Coord entityCoord = Coord.get(entity.getX(), entity.getY());
                List<Coord> path = entity.getPathingMap().findPath(1,
                        new ArrayList<Coord>(),
                        new ArrayList<Coord>(),
                        entityCoord,
                        Coord.get(goal.getX(), goal.getY()));
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
                if (!hasActionPlan()) {
                    logger.debug("DOne actions");
                    fsm.pop();
                    fsm.push(idleState);
                    return;
                }

                GOAPAction action = currentActions.peek();

                currentActions.remove();

                if (hasActionPlan()) {
                    action = currentActions.peek();
                    boolean success = action.perform(entity);
                    if (!success) {
                        fsm.pop();
                        fsm.push(idleState);
                    }
                } else {
                    fsm.pop();
                    fsm.push(idleState);
                }
            }
        };
    }

    private void loadActions() {
    }

}
