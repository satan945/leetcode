/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Stack;

/**
 * 114. Flatten Binary Tree to Linked List
 * <p>
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/5 下午5:30
 * @version $Id$
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flatten(left);
        flatten(right);
        root.right = left;
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        node.right = right;
    }

    public void flattern2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (!stack.isEmpty()) {
                node.right = stack.peek();
            }
            node.left = null;
        }
    }
}
