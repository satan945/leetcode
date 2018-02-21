/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Stack;

/**
 * 230. Kth Smallest Element in a BST
 * <p>
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * 
 * Note: You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * 
 * Follow up: What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
 * frequently? How would you optimize the kthSmallest routine?
 * 
 * Credits: Special thanks to @ts for adding this problem and creating all test cases.
 * </p>
 * 
 * @author abel created on 2018/2/9 下午6:37
 * @version $Id$
 */
public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {
        int cnt = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                cnt++;
                if (cnt == k) {
                    return node.val;
                }
                node = node.right;

            }
        }
        return -1;
    }

    public int kthSmallest2(TreeNode root, int k) {
        int count = countTree(root.left);
        if (k <= count) {
            return kthSmallest2(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest2(root.right, k - count - 1);
        }
        return root.val;
    }

    private int countTree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countTree(node.left) + countTree(node.right);
    }

}
