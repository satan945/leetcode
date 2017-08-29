/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree.travel;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author abel created on 2017/8/25 下午3:51
 * @version $Id$
 */
public class InOrderTraversal {

    // Recursive Solution
    public List<TreeNode> travelR(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        inOrderTravelRecursive(root, res);
        return res;
    }

    private void inOrderTravelRecursive(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        inOrderTravelRecursive(root.left, res);
        res.add(root);
        inOrderTravelRecursive(root.right, res);
    }

    // NonRecursive Solution
    public List<TreeNode> travelNR(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                res.add(node);
                node = node.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
