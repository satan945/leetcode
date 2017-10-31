/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
public class BSTIterator {
    private int pos = 0;
    private List<TreeNode> linkList = new LinkedList<>();

    /**
     * Your BSTIterator will be called like this:
     * 
     * BSTIterator i = new BSTIterator(root); while (i.hasNext()) v[f()] = i.next();
     */
    public BSTIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                linkList.add(node);
                node = node.right;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (linkList.isEmpty() || linkList.size() < pos + 1) {
            return false;
        }
        return true;
    }

    /** @return the next smallest number */
    public int next() {
        if (linkList.isEmpty() || linkList.size() < pos + 1) {
            return 0;
        }
        TreeNode node = linkList.get(pos);
        pos++;
        return node.val;
    }
}
