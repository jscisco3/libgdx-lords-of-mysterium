package com.jscisco.lom.attic.bt.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

    protected Status status;
    protected List<Node> children;

    public Node() {
        this.status = Status.RUNNING;
        this.children = new ArrayList<>();
    }

    public abstract void perform();

    public Status getStatus() {
        return status;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public enum Status {
        SUCCESS,
        RUNNING,
        FAILURE
    }
}
