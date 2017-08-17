/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode.tree.medium;

import apple.laf.JRSUIUtils;
import org.ccs.leetcode.bean.TreeLinkNode;
import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
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

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * <p>
     * <p>
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
     * 
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
     * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of
     * itself).”
     * 
     * _______3______
     *
     * / \
     * 
     * ___5__ ___1__
     *
     * / \ / \
     * 
     * 6 _2 0 8
     * 
     * / \
     * 
     * 7 4
     * 
     * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is
     * 5, since a node can be a descendant of itself according to the LCA definition.
     * </p>
     * 
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public TreeNode lowestCommonAncestorIterate(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        stack.push(root);
        parentMap.put(root, null);
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }

        HashSet<TreeNode> ancestorSet = new HashSet<>();
        while (p != null) {
            ancestorSet.add(p);
            p = parentMap.get(p);
        }
        while (!ancestorSet.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }

    /**
     * 116. Populating Next Right Pointers in Each Node
     * <p>
     * https://leetcode.com/problems/populating-next-right-pointers-in-each-node
     * <p>
     * 
     * Given a binary tree
     * 
     * struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next pointer
     * to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * 
     * Initially, all next pointers are set to NULL.
     * 
     * Note:
     * 
     * You may only use constant extra space. You may assume that it is a perfect binary tree (ie, all leaves are at the
     * same level, and every parent has two children). For example, Given the following perfect binary tree, 1 / \ 2 3 /
     * \ / \ 4 5 6 7 After calling your function, the tree should look like: 1 -> NULL / \ 2 -> 3 -> NULL / \ / \
     * 4->5->6->7 -> NULL
     * </p>
     * 
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        TreeLinkNode pre = root;
        TreeLinkNode node;
        while (pre.left != null) {
            node = pre;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                node = node.next;
            }
            pre = pre.left;
        }
    }

    /**
     * 114. Flatten Binary Tree to Linked List
     * <p>
     * https://leetcode.com/problems/flatten-binary-tree-to-linked-list
     * <p>
     * Given a binary tree, flatten it to a linked list in-place.
     * 
     * For example, Given
     * 
     * 1 / \ 2 5 / \ \ 3 4 6 The flattened tree should look like: 1 \ 2 \ 3 \ 4 \ 5 \ 6
     * </p>
     * ???
     * @param root
     */
    public void flattenRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flattenRecursive(left);
        flattenRecursive(right);

        root.right = left;
        TreeNode node = root;
        while (node.right != null) {
            node = node.right;
        }
        node.right = right;
    }

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null; // dont forget this!!
        }
    }
}
