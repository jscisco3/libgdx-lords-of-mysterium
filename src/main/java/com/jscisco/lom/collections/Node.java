package com.jscisco.lom.collections;

import java.util.LinkedList;
import java.util.List;

public class Node<T> {
    private T value;
    private final List<Node<T>> children = new LinkedList<>();
    private Node<T> parent = null;

    public Node(T value) {
        this.value = value;
    }

    public void addChild(Node<T> node) {
        this.children.add(node);
        node.parent = this;
    }

    public T getValue() {
        return this.value;
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    /**
     * Get the "leaf" nodes from the tree
     *
     * @return The nodes without children
     */
    public List<Node<T>> getLeaves() {
        List<Node<T>> leaves = new LinkedList<>();
        if (this.children.isEmpty()) {
            leaves.add(this);
        }
        for (Node<T> child : this.children) {
            leaves.addAll(child.getLeaves());
        }
        return leaves;
    }
}
