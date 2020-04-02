package com.tree.rb;

public class RBTree<T extends Comparable> {

    private RBNode<T> root;

    private RBNode<T> current;

    private RBNode<T> singleRotateToRight(RBNode<T> top) {
        RBNode<T> newTop = top.left;
        newTop.parent = top.parent;

        top.left = newTop.right;
        if (top.left != null) top.left.parent = top;

        newTop.right = top;
        top.parent = newTop;
        return newTop;
    }

    private RBNode<T> singleRotateToLeft(RBNode<T> top) {
        RBNode<T> newTop = top.right;
        newTop.parent = top.parent;

        top.right = newTop.left;
        if (top.right != null) top.right.parent = top;

        newTop.left = top;
        top.parent = newTop;
        return newTop;
    }

    public void insert(T data) {
        root = insert(data, root);
        balanceInserted();
    }

    private RBNode<T> insert(T data, RBNode<T> node) {
        if (node == null) {
            node = new RBNode<>(data);
            current = node;
        } else {
            if (data.compareTo(node.data) < 0) {
                // 左侧
                node.left = insert(data, node.left);
                node.left.parent = node;
            } else if (data.compareTo(node.data) > 0) {
                // 右侧
                node.right = insert(data, node.right);
                node.right.parent = node;
            }
        }

        return node;
    }

    private void balanceInserted() {
        if (current == null) return;
        if (current == root) {
            root.isRed = false;
            current = null;
            return;
        }
        if (current.parent == root) {
            return;
        }
        RBNode<T> parent = current.parent;
        RBNode<T> grand = parent.parent;
        RBNode<T> great = grand.parent;

        RBNode<T> uncle;

        if (!parent.isRed) return; // parent是黑色 不需要调整

        // parent是红色
        if (parent == grand.left) {
            uncle = grand.right;
            if (uncle == null || !uncle.isRed) {
                // uncle是黑色或者是null
                if (current == parent.left) {
                    // parent 左侧， child左侧
                    grand.isRed = true;
                    parent.isRed = false;
                    if (great == null) {
                        root = singleRotateToRight(grand);
                        root.isRed = false;
                    } else if (great.left == grand){
                        great.left = singleRotateToRight(grand);
                    } else  {
                        great.right = singleRotateToRight(grand);
                    }
                } else {
                    // parent左侧，child右侧
                    grand.left = singleRotateToLeft(parent);
                    current = grand.left.left;
                    balanceInserted();
                }
            } else {
                // uncle是红色
                uncle.isRed = false;
                parent.isRed = false;
                grand.isRed = true;
                if (current == root) {
                    root.isRed = false;
                } else {
                    current = grand;
                    balanceInserted();
                }

            }
        } else {
            uncle = grand.left;
            if (uncle == null || !uncle.isRed) {
                // uncle是黑色或者是null
                if (current == parent.right) {
                    // parent 右侧， child右侧
                    grand.isRed = true;
                    parent.isRed = false;
                    if (great == null) {
                        root = singleRotateToLeft(grand);
                        root.isRed = false;
                    } else if (great.left == grand) {
                        great.left = singleRotateToLeft(grand);
                    } else {
                        great.right = singleRotateToLeft(grand);
                    }
                } else {
                    // parent 右侧， child左侧
                    grand.right = singleRotateToRight(parent);
                    current = grand.right.right;
                    balanceInserted();
                }
            } else {
                // uncle是红色
                uncle.isRed = false;
                parent.isRed = false;
                grand.isRed = true;
                if (current == root) {
                    root.isRed = false;
                } else {
                    current = grand;
                    balanceInserted();
                }
            }
        }

        current = null;

    }

    public void printTree() {
        printTree(root);

    }

    private void printTree(RBNode<T> node) {
        if (node != null) {
            System.out.println(node.toString());
            printTree(node.left);
            printTree(node.right);
        }
    }
}










