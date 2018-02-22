/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 671. Second Minimum Node In a Binary Tree
 * 
 * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
 * 
 * <p>
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree
 * has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among
 * its two sub-nodes.
 * 
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the
 * whole tree.
 * 
 * If no such second minimum value exists, output -1 instead.
 * </p>
 * 
 * @author abel created on 2018/2/21 下午1:44
 * @version $Id$
 */
public class SecondMinimumNodeInABinaryTree {

    private int first = Integer.MAX_VALUE;
    private int second = -1;

    public int findSecondMinimumValue(TreeNode root) {
        preOrderTravel(root);
        return second;
    }

    private void preOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.val < first) {
            if (first < Integer.MAX_VALUE) {
                second = first;
            }
            first = node.val;
        } else {
            if (node.val != first) {
                if (second == -1 || node.val < second) {
                    second = node.val;
                }
            }
        }

        preOrderTravel(node.left);
        preOrderTravel(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(5);
        TreeNode rl = new TreeNode(5);
        TreeNode rr = new TreeNode(7);
        root.left = l;
        root.right = r;
        r.left = rl;
        r.right = rr;
        SecondMinimumNodeInABinaryTree secondMinimumNodeInABinaryTree = new SecondMinimumNodeInABinaryTree();
        System.out.println(secondMinimumNodeInABinaryTree.findSecondMinimumValue(root));
    }
}
