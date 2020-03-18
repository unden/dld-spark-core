package com.duliday.spark.core.leetcode.tree;

public class TreeNodeWithParentCursor {

    private int value;

    private TreeNodeWithParentCursor left;

    private TreeNodeWithParentCursor right;

    private TreeNodeWithParentCursor parent;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNodeWithParentCursor getLeft() {
        return left;
    }

    public void setLeft(TreeNodeWithParentCursor left) {
        this.left = left;
    }

    public TreeNodeWithParentCursor getRight() {
        return right;
    }

    public void setRight(TreeNodeWithParentCursor right) {
        this.right = right;
    }

    public TreeNodeWithParentCursor getParent() {
        return parent;
    }

    public void setParent(TreeNodeWithParentCursor parent) {
        this.parent = parent;
    }
}
