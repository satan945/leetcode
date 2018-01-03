/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree.travel;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author abel created on 2017/8/25 下午3:51
 * @version $Id$
 */
public class PostOrderTraversal {

    // Recursive
    public List<TreeNode> travelR(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postOrderTravelRecursive(root, res);
        return res;
    }

    private void postOrderTravelRecursive(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        postOrderTravelRecursive(root.left, res);
        postOrderTravelRecursive(root.right, res);
        res.add(root);
    }

    // NonRecursive
    // using two stack

    // using linked list

    // using one stack and reverse
    private void postOrderTravelOneStack(TreeNode root, List<TreeNode> res) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            stack.pop();
            if (!stack.isEmpty() && node == stack.peek()) {
                if (node.right != null) {
                    stack.push(node.right);
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                    stack.push(node.left);
                }
            } else {
                res.add(node);
            }
        }
    }

}
