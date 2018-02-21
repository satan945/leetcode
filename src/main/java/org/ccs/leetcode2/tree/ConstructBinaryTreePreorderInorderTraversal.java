/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * </p>
 * 
 * @author abel created on 2018/1/3 下午4:12
 * @version $Id$
 */
public class ConstructBinaryTreePreorderInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
                || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return dfsBuildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode dfsBuildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inorderMap.get(preorder[preStart]);
        int nodes = inRoot - inStart;
        root.left = dfsBuildTree(preorder, preStart + 1, preStart + nodes, inStart, inRoot - 1, inorderMap);
        root.right = dfsBuildTree(preorder, preStart + nodes + 1, preEnd, inRoot + 1, inEnd, inorderMap);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreePreorderInorderTraversal solution = new ConstructBinaryTreePreorderInorderTraversal();
        solution.buildTree(new int[] { 1, 2 }, new int[] { 1, 2 });
    }
}
