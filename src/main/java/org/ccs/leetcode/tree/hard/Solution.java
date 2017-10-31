/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.hard;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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

    /**
     * 145. Binary Tree Postorder Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-postorder-traversal
     * 
     * <p>
     * Given a binary tree, return the postorder traversal of its nodes' values.
     *
     *
     * Note: Recursive solution is trivial, could you do it iteratively?
     * </p>
     * 
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalReverse(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.add(node.val);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        Collections.reverse(res);
        return res;

    }

    public List<Integer> postorderTraversalLinkedList(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return ans;
        }

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            ans.addFirst(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return ans;
    }

    public List<Integer> postorderTraversalTwoStack(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        if (root == null)
            return res;

        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        s1.push(root);

        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);

            if (node.left != null) {
                s1.push(node.left);
            }

            if (node.right != null) {
                s1.push(node.right);
            }
        }

        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }

        return res;
    }

    /**
     * http://www.cnblogs.com/rain-lei/p/3705680.html
     * 
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversalOneStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        stack.push(node);
        stack.push(node);
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
        return res;
    }
}
