/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * 
 * @author Abel created on 2018/2/14 18:47
 * @version $Id$
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.addFirst(node.val);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        return res;
    }

}
