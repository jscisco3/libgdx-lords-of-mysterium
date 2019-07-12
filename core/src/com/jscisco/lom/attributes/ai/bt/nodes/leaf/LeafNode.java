package com.jscisco.lom.attributes.ai.bt.nodes.leaf;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LeafNode extends Node {

    private static final Logger logger = LoggerFactory.getLogger(LeafNode.class);

    public LeafNode() {
        super();
    }

    public abstract void init();

    @Override
    public void perform() {
    }

    @Override
    public void addChild(Node node) {
        logger.warn("Cannot add children nodes to leaf nodes");
        // Do nothing.
    }
}
