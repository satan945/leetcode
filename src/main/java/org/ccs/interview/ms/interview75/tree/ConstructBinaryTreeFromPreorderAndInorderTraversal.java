/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * <p>
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * </p>
 * 
 * @author abel created on 2018/2/24 下午1:37
 * @version $Id$
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return buildByPreAndInOrder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderIndexMap);
    }

    private TreeNode buildByPreAndInOrder(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart,
            int inEnd, Map<Integer, Integer> inOrderIndexMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int inRootIndex = inOrderIndexMap.get(rootVal);
        int nodeNum = inRootIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildByPreAndInOrder(preorder, preStart + 1, preStart + nodeNum, inorder, inStart, inRootIndex - 1,
                inOrderIndexMap);
        root.right = buildByPreAndInOrder(preorder, preStart + nodeNum + 1, preEnd, inorder, inRootIndex + 1, inEnd,
                inOrderIndexMap);
        return root;

    }
}
