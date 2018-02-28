/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * 
 * @author abel created on 2018/2/24 下午1:29
 * @version $Id$
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.putIfAbsent(inorder[i], i);
        }
        return buildByInAndPost(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inOrderIndexMap);
    }

    private TreeNode buildByInAndPost(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart,
            int postEnd, Map<Integer, Integer> inOrderIndexMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int inRootIndex = inOrderIndexMap.get(rootVal);
        int nodeNums = inRootIndex - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildByInAndPost(inorder, inStart, inStart + nodeNums - 1, postorder, postStart,
                postStart + nodeNums - 1, inOrderIndexMap);
        root.right = buildByInAndPost(inorder, inRootIndex + 1, inEnd, postorder, postStart + nodeNums, postEnd - 1,
                inOrderIndexMap);
        return root;
    }
}
