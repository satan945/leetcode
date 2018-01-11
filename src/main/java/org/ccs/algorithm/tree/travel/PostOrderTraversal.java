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
    public List<Integer> travelR(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postOrderTravelOneStack(root, res);
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
    private void postOrderTravelOneStack(TreeNode root, List<Integer> res) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
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
                res.add(node.val);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode lr = new TreeNode(5);
        TreeNode rl = new TreeNode(6);
        TreeNode rr = new TreeNode(7);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.left = rl;
        r.right = rr;
        PostOrderTraversal postOrderTraversal = new PostOrderTraversal();
        System.out.println(postOrderTraversal.travelR(root));
    }

}
