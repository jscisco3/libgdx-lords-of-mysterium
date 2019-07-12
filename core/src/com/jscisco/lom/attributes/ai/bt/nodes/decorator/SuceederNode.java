package com.jscisco.lom.attributes.ai.bt.nodes.decorator;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;

public class SuceederNode extends DecoratorNode {

    public SuceederNode(Node node) {
        super(node);
    }

    @Override
    public void perform() {
        super.perform();
        // Always succeed!
        this.status = Status.SUCCESS;
    }
}
