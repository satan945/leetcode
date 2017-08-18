/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.hard;

import org.ccs.leetcode.bean.TreeNode;

/**
 * @author Abel created on 2017/8/17 13:48
 * @version $Id$
 */
public class Solution {

    /**
     * 124. Binary Tree Maximum Path Sum
     * <p>
     * https://leetcode.com/problems/binary-tree-maximum-path-sum
     * <p>
     * Given a binary tree, find the maximum path sum.
     * 
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The path must contain at least one node and does not need to go through the
     * root.
     * 
     * For example: Given the below binary tree,
     * 
     * 1
     * 
     * / \
     * 
     * 2 3
     * 
     * Return 6.
     * </p>
     * https://discuss.leetcode.com/category/132/binary-tree-maximum-path-sum
     * 
     * https://discuss.leetcode.com/topic/17823/elegant-java-solution/4
     * 
     * @param root
     * @return
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calMaxSum(root);
        return maxSum;
    }

    private int calMaxSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(calMaxSum(root.left), 0);
        int right = Math.max(calMaxSum(root.right), 0);
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);
    }
}
