/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.ccs.leetcode.bean.TreeNode;
import org.ccs.utils.TreeDeserializer;

/**
 * 145. Binary Tree Postorder Traversal
 * 
 * @author Abel created on 2018/2/14 18:47
 * @version $Id$
 */
public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode lr = new TreeNode(5);
        TreeNode rl = new TreeNode(6);
        TreeNode rr = new TreeNode(7);
        root.left=l;
        root.right=r;
        l.left=ll;
        l.right=lr;
        r.left=rl;
        r.right=rr;
        System.out.println(new BinaryTreePreorderTraversal().preorderTraversal(root));
    }

}
