/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 671. Second Minimum Node In a Binary Tree
 *
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree
 * <p>
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree
 * has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among
 * its two sub-nodes.
 * 
 * 
 * </p>
 * 
 * @author abel created on 2018/2/22 下午3:53
 * @version $Id$
 */
public class SecondMinimumNodeInBinaryTree {

    // the tree in this problem is very specific, the min value is the value of the root,so we can travel the whole tree
    // to find
    private int second = -1;

    public int findSecondMinimumValue(TreeNode root) {
        int rootVal = root.val;
        preOrder(root, root.val);
        return second;
    }

    private void preOrder(TreeNode root, int val) {
        if (root == null) {
            return;
        }
        if (root.val != val && (second == -1 || root.val < second)) {
            second = root.val;
        }
        preOrder(root.left, val);
        preOrder(root.right, val);
    }
}
