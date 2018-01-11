/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree.travel;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author abel created on 2018/1/11 上午11:41
 * @version $Id$
 */
public class Traversal {

    public List<Integer> preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            res.add(node.val);
        }
        return res;
    }

    public List<Integer> inOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }

    public List<Integer> postOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
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
        Traversal traversal = new Traversal();
        System.out.println(traversal.preOrder(root));
        System.out.println(traversal.inOrder(root));
        System.out.println(traversal.postOrder(root));
    }
}
