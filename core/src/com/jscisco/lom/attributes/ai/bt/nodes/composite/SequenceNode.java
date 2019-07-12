package com.jscisco.lom.attributes.ai.bt.nodes.composite;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;

public class SequenceNode extends CompositeNode {

    public SequenceNode() {
        super();
    }

    @Override
    public void perform() {
        this.status = Status.RUNNING;
        Status leafStatus = Status.RUNNING;
        for (Node child : this.children) {
            child.perform();
            leafStatus = child.getStatus();
            if (leafStatus == Status.FAILURE) {
                this.status = Status.FAILURE;
                return;
            }
        }
    }
}
