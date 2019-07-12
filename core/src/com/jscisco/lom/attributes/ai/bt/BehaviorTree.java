package com.jscisco.lom.attributes.ai.bt;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;

public interface BehaviorTree {

    /**
     * Add a child node to the provided parent node
     *
     * @param parent
     * @param node
     */
    void addNode(Node parent, Node node);

    /**
     * Process the behavior tree and set
     */
    void process();

}
