package com.duliday.spark.core.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
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

    /****************************** <begin> ***********************************/
    /**
     * @param root
     * @param p
     * @param q
     * @return 二叉树的最近公共祖先
     * 情况一：如果每个节点保存了指向父节点的指针，则转变为求两个链表的交点，参考leetcode：160
     */
    public TreeNode lowestCommonAncestorUseParentPoint(TreeNode root, TreeNode p, TreeNode q) {
        return null;
    }

    /**
     * @param root
     * @param p
     * @param q
     * @return 二叉树的最近公共祖先
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
     * @return 二叉搜索树
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

    /****************************** <end> ***********************************/

    public static void main(String[] args) {
        /*List<Integer> result = new ArrayList<Integer>();
        inOrder(root, result);
        preOrder(root, result);
        postOrder(root, result);
        System.out.println(lowestCommonAncestor(root, root.getLeft().getLeft(), root.getLeft().getRight()).getValue());
        System.out.println(lowestCommonAncestorOfBinarySearchTree(root, root.getLeft().getLeft(), root.getLeft().getRight()).getValue());*/
        System.out.println(treeDepth(root));
    }

    /****************************** <begin> ***********************************/
    /**
     * @param root
     * @param result 中序遍历
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
     * @param result 前序遍历
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
     * @param result 后序遍历
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
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * 不分行从上往下打印二叉树
     * @param root
     */
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        System.out.println();
    }


    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.value);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
    }



    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * @param root
     * @return 二叉树的深度
     * 如果一棵树只有一个节点，那么它的深度为1
     * 如果根节点只有左子树而没有右子树，那么树的深度为左子树的深度+1；同理，如果根节点只有右子树而没有左子树，那么树的深度为右子树的深度+1
     * 如果根节点既有左子树又有右子树，那么树的深度为左、右子树深度的较大值+1
     */
    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = treeDepth(root.getLeft());
        int rightDepth = treeDepth(root.getRight());
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }
    /****************************** <end> ***********************************/


    /****************************** <begin> ***********************************/
    /**
     * 给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
     * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
     * 剑指Offer：7
     *
     * @return
     */
    public TreeNodeWithParentCursor nextNodeInBinaryTree(TreeNodeWithParentCursor target) {
        if (target == null) {
            return null;
        }

        // 如果该节点的右节点不为空，则下一个节点为右节点的最左子节点
        if (target.getRight() != null) {
            target = target.getRight();
            while (target.getLeft() != null) {
                target = target.getLeft();
            }
            return target;
        }

        // 如果该节点的右节点为空，则下一个节点为：当该节点为其父节点的左子节点，则其父节点为下一个节点，递归判断
        while (target.getParent() != null) {
            if (target == target.getParent().getLeft()) {
                return target.getParent();
            }
            target = target.getParent();
        }

        return null;
    }

    /****************************** <end> ***********************************/


    /****************************** <begin> ***********************************/
    /**
     * 判断是否为平衡二叉树
     */
    public boolean isBalancedTree(TreeNode root) {
        return computeDepth(root) != -1;
    }

    public int computeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = computeDepth(root.left);
        if (left == -1) {
            return -1;
        }

        int right = computeDepth(root.right);
        if (right == -1) {
            return -1;
        }

        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
    /****************************** <end> ***********************************/

    /****************************** <begin> ***********************************/
    /**
     * 路径总和
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.value;
        if ((root.left == null) && (root.right == null)) {
            return (sum == 0);
        }

        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
    /****************************** <end> ***********************************/

}
