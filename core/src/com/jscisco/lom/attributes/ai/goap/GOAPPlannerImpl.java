package com.jscisco.lom.attributes.ai.goap;

import com.jscisco.lom.attributes.ai.goap.actions.GOAPAction;
import com.jscisco.lom.attributes.ai.goap.actions.GOAPGoal;
import com.jscisco.lom.entity.Entity;

import java.util.*;

public class GOAPPlannerImpl implements GOAPPlanner {

    @Override
    public Queue<GOAPAction> plan(Entity entity, Set<GOAPAction> availableActions, Map<GOAPGoal, Object> worldState, Map<GOAPGoal, Object> goal) {
        // Reset all the actions so they are fresh
        for (GOAPAction action : availableActions) {
            action.reset();
        }

        // Get the usable actions by checking their preconditions
        Set<GOAPAction> usableActions = new HashSet<>();
        for (GOAPAction action : availableActions) {
            if (action.checkProceduralPreconditions(entity)) {
                usableActions.add(action);
            }
        }

        // Build up the tree and record the leaf nodes that provide solution to goal.
        List<Node> leaves = new ArrayList<>();
        Node start = new Node(null, 0, worldState, null);
        boolean success = buildGraph(start, leaves, usableActions, goal);

        if (!success) {
            return null;
        }

        Node cheapest = null;
        for (Node node : leaves) {
            if (cheapest == null) {
                cheapest = node;
            } else {
                if (node.runningCost < cheapest.runningCost) {
                    cheapest = node;
                }
            }
        }

        // Get its node and start working towards the parent.
        List<GOAPAction> result = new ArrayList<>();
        Node node = cheapest;
        while (node != null) {
            if (node.action != null) {
                result.add(0, node.action);
            }
            node = node.parent;
        }

        return new ArrayDeque<>(result);
    }

    /**
     * Returns true if at least one solution was found.
     * The possible paths are stored in the leaves list, each of which
     * has the running cost associated with the plan. The lower cost is the one
     * we should choose.
     *
     * @param parent
     * @param leaves
     * @param usableActions
     * @param goal
     * @return
     */
    private boolean buildGraph(Node parent, List<Node> leaves, Set<GOAPAction> usableActions, Map<GOAPGoal, Object> goal) {
        boolean foundOne = false;

        for (GOAPAction action : usableActions) {
            // If the parent state has the conditions for this action's preconditions, we can use it here
            Map<GOAPGoal, Object> preconditions = action.getPreconditions();
            if (allInState(preconditions, parent.state)) {
                Map<GOAPGoal, Object> currentState = applyStateChange(parent.state, action.getEffects());
                Node node = new Node(parent, parent.runningCost + action.getCost(), currentState, action);
                if (allInState(goal, currentState)) {
                    // goal is in the current state, so we have found a solution!
                    leaves.add(node);
                    foundOne = true;
                } else {
                    // Not at a solution yet, so test all remaining actions and branch out the tree
                    Set<GOAPAction> subset = actionSubset(usableActions, action);
                    boolean found = buildGraph(node, leaves, subset, goal);
                    if (found) {
                        foundOne = true;
                    }
                }
            }
        }

        return foundOne;
    }

    private boolean allInState(Map<GOAPGoal, Object> test, Map<GOAPGoal, Object> state) {
        return state.entrySet().containsAll(test.entrySet());
    }

    private Map<GOAPGoal, Object> applyStateChange(Map<GOAPGoal, Object> currentState, Map<GOAPGoal, Object> stateChange) {
        Map<GOAPGoal, Object> state = new HashMap<>();

        state.putAll(currentState);

        for (GOAPGoal key : stateChange.keySet()) {
            if (state.containsKey(key)) {
                state.replace(key, stateChange.get(key));
            } else {
                state.put(key, stateChange.get(key));
            }
        }

        return state;
    }

    private Set<GOAPAction> actionSubset(Set<GOAPAction> actions, GOAPAction removeMe) {
        Set<GOAPAction> subset = new HashSet<>();
        for (GOAPAction action : actions) {
            if (!(action.equals(removeMe))) {
                subset.add(action);
            }
        }
        return subset;
    }

    private class Node {
        private Node parent;
        private float runningCost;
        private Map<GOAPGoal, Object> state;
        private GOAPAction action;

        public Node(Node parent, float runningCost, Map<GOAPGoal, Object> state, GOAPAction action) {
            this.parent = parent;
            this.runningCost = runningCost;
            this.state = state;
            this.action = action;
        }
    }
}
