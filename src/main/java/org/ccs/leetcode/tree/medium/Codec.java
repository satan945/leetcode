/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 449. Serialize and Deserialize BST
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 * </p>
 * 
 * @author Abel created on 2017/11/6 16:55
 * @version $Id$
 */
public class Codec {
    private static final String SPLITTER = "#";
    private static final String NULL = "null";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return NULL;
        }
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            sb.append(node.val).append(SPLITTER);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (NULL.equals(data)) {
            return null;
        }
        String[] nodeStrings = data.split(SPLITTER);
        Queue<Integer> queue = new LinkedList<>();
        for (String node : nodeStrings) {
            queue.offer(Integer.parseInt(node));
        }
        return getNodes(queue);
    }

    private TreeNode getNodes(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(queue.poll());
        Queue<Integer> smallQueue = new LinkedList<>();
        while (!queue.isEmpty() && queue.peek() < root.val) {
            smallQueue.offer(queue.poll());
        }
        root.left = getNodes(smallQueue);
        root.right = getNodes(queue);
        return root;
    }
}
