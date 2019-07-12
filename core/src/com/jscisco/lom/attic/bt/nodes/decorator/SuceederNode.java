package com.jscisco.lom.attic.bt.nodes.decorator;

import com.jscisco.lom.attic.bt.nodes.Node;

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
