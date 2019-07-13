package com.jscisco.lom.attic.goap;

import com.jscisco.lom.attic.goap.actions.GOAPGoal;
import com.jscisco.lom.attic.goap.actions.GOAPAction;
import com.jscisco.lom.entity.Entity;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public interface GOAPPlanner {

    public Queue<GOAPAction> plan(Entity entity, Set<GOAPAction> availableActions, Map<GOAPGoal, Object> worldState, Map<GOAPGoal, Object> goal);
}
