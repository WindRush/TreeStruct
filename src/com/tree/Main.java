package com.tree;

import com.tree.rb.RBTree;

public class Main {
    public static void main(String[] args) {
        testRBTree();
    }

    static void testRBTree() {
        RBTree<Integer> tree = new RBTree<>();
        tree.insert(1);
        tree.insert(21);
        tree.insert(20);
        tree.insert(23);
        tree.insert(56);
        tree.insert(69);
        tree.insert(45);
        tree.insert(40);
        tree.insert(46);
        tree.insert(77);
        tree.insert(200);
        tree.insert(89);
        tree.insert(72);
        tree.insert(63);
        tree.insert(102);

        System.out.println(tree);

        tree.printTree();
    }
}
