/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.interview.ms.interview75.tree;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * </p>
 * 
 * @author abel created on 2018/2/24 下午2:14
 * @version $Id$
 */
public class LowestCommonAncestorOfBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = root.val > p.val ? root.left : root.right;
        }
        return root;
    }
}
