/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Stack;

/**
 * 783. Minimum Distance Between BST Nodes
 * <p>
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two
 * different nodes in the tree.
 * </p>
 * 
 * @author Abel created on 2018/2/11 19:05
 * @version $Id$
 */
public class MinimumDistanceBetweenBSTNodes {

    public int minDiffInBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int min = Integer.MAX_VALUE;
        Integer pre = null;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (pre != null) {
                    min = Math.min(min, Math.abs(node.val - pre));
                }
                pre = node.val;
                node = node.right;
            }
        }
        return min;
    }

}
