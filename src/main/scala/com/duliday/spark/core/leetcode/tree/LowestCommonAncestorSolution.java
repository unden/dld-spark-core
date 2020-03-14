package com.duliday.spark.core.leetcode.tree;

import java.util.List;

public class LowestCommonAncestorSolution {

    private static TreeNode root;

    static {
        TreeNode one = new TreeNode(1, null, null);
        TreeNode three = new TreeNode(3, null, null);
        TreeNode two = new TreeNode(2, one, three);

        TreeNode five = new TreeNode(5, null, null);
        TreeNode seven = new TreeNode(7, null, null);
        TreeNode six = new TreeNode(6, five, seven);

        root = new TreeNode(4, two, six);
    }


    /**
     * @param root
     * @param p
     * @param q
     * @return
     * 二叉树的最近公共祖先
     * 情况一：如果每个节点保存了指向父节点的指针，则转变为求两个链表的交点，参考leetcode：160
     */
    public TreeNode lowestCommonAncestorUseParentPoint(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     * 二叉树的最近公共祖先
     * 情况二：递归解法，参考leetcode：236
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return
     * 二叉搜索树
     * 参考leetcode：235
     */
    public static TreeNode lowestCommonAncestorOfBinarySearchTree(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.getValue() - p.getValue()) * (root.getValue() - q.getValue()) > 0) {
            if (root.getValue() > p.getValue()) {
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        return root;
    }

    public static void main(String[] args) {
        /*List<Integer> result = new ArrayList<Integer>();
        inOrder(root, result);
        preOrder(root, result);
        postOrder(root, result);*/
        System.out.println(lowestCommonAncestor(root, root.getLeft().getLeft(), root.getLeft().getRight()).getValue());
        System.out.println(lowestCommonAncestorOfBinarySearchTree(root, root.getLeft().getLeft(), root.getLeft().getRight()).getValue());
    }


    /**
     * @param root
     * @param result
     * 中序遍历
     */
    public static void inOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inOrder(root.getLeft(), result);
        System.out.println(root.getValue());
        result.add(root.getValue());
        inOrder(root.getRight(), result);
    }

    /**
     * @param root
     * @param result
     * 前序遍历
     */
    public static void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        System.out.println(root.getValue());
//        result.add(root.getValue());
        preOrder(root.getLeft(), result);
        preOrder(root.getRight(), result);
    }

    /**
     * @param root
     * @param result
     * 后序遍历
     */
    public static void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

//        result.add(root.getValue());
        postOrder(root.getLeft(), result);
        postOrder(root.getRight(), result);
        System.out.println(root.getValue());
    }


}
