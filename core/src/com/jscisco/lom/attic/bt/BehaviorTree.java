package com.jscisco.lom.attic.bt;

import com.jscisco.lom.attic.bt.nodes.Node;

public interface BehaviorTree {

    void addNode(Node parent, Node node);

    /**
     * Process the behavior tree and set
     */
    void process();

}
