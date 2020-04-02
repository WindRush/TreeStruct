package com.tree.rb;

public class RBNode<T extends Comparable> {

    public T data;
    public RBNode<T> left;
    public RBNode<T> right;
    public RBNode<T> parent;
    public boolean isRed = true;

    public RBNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{data:" + data.toString() + ",color:" + (isRed ? "red" : "black") + "}";
    }
}
