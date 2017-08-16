/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import java.util.Stack;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 173. Binary Search Tree Iterator
 * <p>
 * https://leetcode.com/problems/binary-search-tree-iterator/description/
 * <p>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * Credits: Special thanks to @ts for adding this problem and creating all test cases.
 * 
 * </p>
 * 
 * @author abel created on 2017/8/16 下午4:30
 * @version $Id$
 */
public class BSTIterator2 {
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        pushAllNodes(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode minNode = stack.pop();
        pushAllNodes(minNode.right);
        return minNode.val;
    }

    private void pushAllNodes(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
