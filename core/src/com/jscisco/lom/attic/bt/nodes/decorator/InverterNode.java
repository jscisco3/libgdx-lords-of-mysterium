package com.jscisco.lom.attic.bt.nodes.decorator;

import com.jscisco.lom.attic.bt.nodes.Node;

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
