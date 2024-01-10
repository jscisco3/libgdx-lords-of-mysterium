package com.jscisco.lom.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    public void instantiateRoot() {
        Node<Integer> root = new Node<>(10);
        assertNull(root.getParent());
        assertTrue(root.getChildren().isEmpty());
        assertEquals(root.getValue(), 10);
    }

    @Test
    public void addChild() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> child = new Node<>(5);
        root.addChild(child);

        assertEquals(root.getChildren().size(), 1);
    }

    @Test
    public void getLeavesJustRoot() {
        Node<Integer> root = new Node<>(10);

        List<Node<Integer>> leaves = root.getLeaves();
        assertEquals(leaves.size(), 1);
    }

    @Test
    public void getLeavesOneChild() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> child = new Node<>(5);
        root.addChild(child);
        List<Node<Integer>> leaves = root.getLeaves();
        assertEquals(leaves.size(), 1);
        assertEquals(leaves.getFirst().getValue(), 5);
    }

    @Test
    public void getLeavesTwoChildren() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> child = new Node<>(5);
        Node<Integer> child2 = new Node<>(7);

        root.addChild(child);
        root.addChild(child2);

        List<Node<Integer>> leaves = root.getLeaves();
        assertEquals(leaves.size(), 2);
    }

    @Test
    public void getLeavesComplex() {
        Node<Integer> root = new Node<>(10);
        Node<Integer> child = new Node<>(5);

        Node<Integer> subTree1 = new Node<>(6);
        Node<Integer> subTreeChild1 = new Node<>(1);
        Node<Integer> subTreeChild2 = new Node<>(2);
        Node<Integer> subTreeChild3 = new Node<>(3);

        Node<Integer> subSubTreeChild1 = new Node<>(3);
        Node<Integer> subSubTreeChild2 = new Node<>(5);

        root.addChild(child);
        root.addChild(subTree1);
        subTree1.addChild(subTreeChild1);
        subTree1.addChild(subTreeChild2);
        subTree1.addChild(subTreeChild3);

        subTreeChild1.addChild(subSubTreeChild1);
        subTreeChild1.addChild(subSubTreeChild2);

        List<Node<Integer>> leaves = root.getLeaves();
        assertEquals(leaves.size(), 5);
    }
}
