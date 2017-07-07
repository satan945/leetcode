/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.binarytree.easy;

import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/7/7 11:47
 * @version $Id$
 */
public class Solution {

    /**
     * todo
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "()";
        }
        String leftStr = tree2str(t.left);
        String rightStr = tree2str(t.right);
        return t.val + "(" + leftStr + rightStr + ")";
    }

    /**
     * 226 Invert Binary Tree
     *
     * <p>
     * https://leetcode.com/problems/invert-binary-tree
     *
     * @param root
     * @return
     */

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 104 Maximum Depth of Binary Tree
     * <p>
     * https://leetcode.com/problems/maximum-depth-of-binary-tree
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int maxDepth = 1;
        int leftDepth = 0;
        if (root.left != null) {
            leftDepth = maxDepth(root.left);
        }
        int rightDepth = 0;
        if (root.right != null) {
            rightDepth = maxDepth(root.right);
        }
        if (leftDepth > rightDepth) {
            return maxDepth + leftDepth;
        } else {
            return maxDepth + rightDepth;
        }
    }

    /**
     * 623. Add One Row to Tree
     *
     * <P>
     *
     * https://leetcode.com/problems/add-one-row-to-tree
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        TreeNode parent;
        TreeNode lChild;
        TreeNode rChild;
        return null;
    }

    /**
     * 100. Same Tree QuestionEditorial Solution My Submissions Total Accepted: 144793 Total Submissions: 329034
     * Difficulty: Easy Given two binary trees, write a function to check if they are equal or not.
     *
     * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            boolean leftSame = isSameTree(p.left, q.left);
            boolean rightSame = isSameTree(p.right, q.right);
            return leftSame && rightSame;
        }
        return false;
    }
}
