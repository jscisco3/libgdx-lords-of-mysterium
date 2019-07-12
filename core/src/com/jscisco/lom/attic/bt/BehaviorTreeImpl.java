package com.jscisco.lom.attic.bt;

import com.jscisco.lom.attic.bt.nodes.Node;

public class BehaviorTreeImpl implements BehaviorTree {

    private Node root;
    private Node currentlyProcessing;

    /**
     * Implementation of Behavior tree
     *
     * @param root the root of the tree
     */
    public BehaviorTreeImpl(Node root) {
        this.root = root;
        this.currentlyProcessing = null;
    }

    @Override
    public void process() {

    }

    @Override
    public void addNode(Node parent, Node node) {
        parent.addChild(node);
    }

}
