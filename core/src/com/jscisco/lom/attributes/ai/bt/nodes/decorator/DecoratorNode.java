package com.jscisco.lom.attributes.ai.bt.nodes.decorator;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;

/**
 * Decorator nodes perform their child node and then transform the result
 */
public abstract class DecoratorNode extends Node {

    // A decorator must have a child node!
    public DecoratorNode(Node node) {
        super();
        this.addChild(node);
    }

    @Override
    public void perform() {
        // A decorator cannot perform if it does not have a child!
        assert !this.children.isEmpty();
        Node child = this.children.get(0);
        child.perform();
        this.status = child.getStatus();
    }

    @Override
    public void addChild(Node node) {
        // Decorator nodes can only have one child
        assert this.children.isEmpty();
        super.addChild(node);
    }
}
