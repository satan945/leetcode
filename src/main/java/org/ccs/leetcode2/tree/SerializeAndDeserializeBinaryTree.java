/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 297. Serialize and Deserialize Binary Tree
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * </p>
 * 
 * @author abel created on 2018/2/5 下午4:35
 * @version $Id$
 */
public class SerializeAndDeserializeBinaryTree {
    private static final String NN = "#";
    private static final String SPLITTER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder treeBuilder = new StringBuilder();
        buildString(root, treeBuilder);
        return treeBuilder.toString();

    }

    private void buildString(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NN).append(SPLITTER);
        } else {
            sb.append(root.val).append(SPLITTER);
            buildString(root.left, sb);
            buildString(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(SPLITTER)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(LinkedList<String> nodes) {
        String node = nodes.remove();
        if (node.equals(NN)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(node));
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode lr = new TreeNode(5);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        SerializeAndDeserializeBinaryTree solution = new SerializeAndDeserializeBinaryTree();
        String data = solution.serialize(null);
        System.out.println(data);
        TreeNode node = solution.deserialize(data);
        System.out.println(node);
    }
}
