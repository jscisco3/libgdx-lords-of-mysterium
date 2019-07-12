package com.jscisco.lom.attributes.ai.bt.nodes.decorator;

import com.jscisco.lom.attributes.ai.bt.nodes.Node;

public class InverterNode extends DecoratorNode {

    public InverterNode(Node node) {
        super(node);
    }

    @Override
    public void perform() {
        super.perform();
        switch (this.status) {
            case FAILURE:
                this.status = Status.SUCCESS;
                break;
            case SUCCESS:
                this.status = Status.FAILURE;
                break;
            case RUNNING:
            default:
                break;
        }
    }
}
