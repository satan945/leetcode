/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.amazon;

import org.ccs.leetcode.bean.TreeNode;
import org.ccs.leetcode.design.hard.DoubleLinkedNode;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
 * 
 * @author Abel created on 2018/1/30 0:14
 * @version $Id$
 */
public class ConvertBinaryTreeToDoublyLinkedList {
    public DoubleLinkedNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        DoubleLinkedNode dRoot = new DoubleLinkedNode(-1, -1);
        DoubleLinkedNode dCur = dRoot;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            DoubleLinkedNode dNode = new DoubleLinkedNode(-1, node.val);
            dCur.next = dNode;
            dNode.pre = dCur;
            dCur = dNode;
            node = node.right;
        }
        dCur = dRoot.next;
        dCur.pre = null;
        return dCur;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode l = new TreeNode(12);
        TreeNode r = new TreeNode(15);
        TreeNode ll = new TreeNode(25);
        TreeNode lr = new TreeNode(30);
        TreeNode rl = new TreeNode(36);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.left = rl;
        DoubleLinkedNode node = new ConvertBinaryTreeToDoublyLinkedList().convert(root);
        System.out.println(node);

    }
}
