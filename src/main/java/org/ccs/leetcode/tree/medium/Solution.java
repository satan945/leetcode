/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Abel created on 2017/7/19 17:12
 * @version $Id$
 */
public class Solution {
    /**
     * 623. Add One Row to Tree
     *
     * <P>
     *
     * https://leetcode.com/problems/add-one-row-to-tree
     * 
     * <p>
     * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the
     * given depth d. The root node is at depth 1.
     * 
     * The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two
     * tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be
     * the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new
     * right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v
     * as the new root of the whole original tree, and the original tree is the new root's left subtree.
     * 
     * Example 1: Input: A binary tree as following: 4 / \ 2 6 / \ / 3 1 5
     * 
     * v = 1
     * 
     * d = 2
     * 
     * Output: 4 / \ 1 1 / \ 2 6 / \ / 3 1 5
     * 
     * Example 2: Input: A binary tree as following: 4 / 2 / \ 3 1
     * 
     * v = 1
     * 
     * d = 3
     * 
     * Output: 4 / 2 / \ 1 1 / \ 3 1 Note: The given d is in range [1, maximum depth of the given tree + 1]. The given
     * binary tree has at least one tree node.
     * </p>
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {

        return null;
    }

    /**
     * 144. Binary Tree Preorder Traversal
     * <p>
     * https://leetcode.com/problems/binary-tree-preorder-traversal
     * 
     * <p>
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * 
     * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [1,2,3].
     * 
     * Note: Recursive solution is trivial, could you do it iteratively?
     * </p>
     * 
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                result.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }

    /**
     * 94. Binary Tree Inorder Traversal
     *
     * <p>
     * https://leetcode.com/problems/binary-tree-inorder-traversal
     * <p>
     * Given a binary tree, return the inorder traversal of its nodes' values.
     *
     * For example: Given binary tree [1,null,2,3], 1 \ 2 / 3 return [1,3,2].
     *
     * Note: Recursive solution is trivial, could you do it iteratively?
     * </p>
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     *
     * <p>
     * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     *
     * <p>
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     *
     * </p>
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeByInAndPostOrder(int[] inorder, int[] postorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeRecursiveByInAndPostOrder(inorder, 0, inorder.length - 1, postorder, 0,
                postorder.length - 1, indexMap);
        return root;
    }

    private TreeNode buildTreeRecursiveByInAndPostOrder(int[] inorder, int inStart, int inEnd, int[] postorder,
            int postStart, int postEnd, Map<Integer, Integer> indexMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postEnd]);

        int inRoot = indexMap.get(postorder[postEnd]);
        int nodeNums = inRoot - inStart;
        root.left = buildTreeRecursiveByInAndPostOrder(inorder, inStart, inRoot - 1, postorder, postStart,
                postStart + nodeNums - 1, indexMap);
        root.right = buildTreeRecursiveByInAndPostOrder(inorder, inRoot + 1, inEnd, postorder, postStart + nodeNums,
                postEnd - 1, indexMap);
        return root;
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * <p>
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/
     * <p>
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note: You may assume that duplicates do not exist in the tree.
     * </p>
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreAndInOrder(int[] preorder, int[] inorder) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTreeRecursiveByPreAndInOrder(preorder, 0, preorder.length - 1, inorder, 0,
                inorder.length - 1, indexMap);
        return root;
    }

    private TreeNode buildTreeRecursiveByPreAndInOrder(int[] preorder, int preStart, int preEnd, int[] inorder,
            int inStart, int inEnd, Map<Integer, Integer> indexMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = indexMap.get(preorder[preStart]);
        int nodenums = inRoot - inStart;
        root.left = buildTreeRecursiveByPreAndInOrder(preorder, preStart + 1, preStart + nodenums, inorder, inStart,
                inRoot - 1, indexMap);
        root.right = buildTreeRecursiveByPreAndInOrder(preorder, preStart + nodenums, preEnd, inorder, inRoot + 1,
                inEnd, indexMap);
        return root;
    }
}
