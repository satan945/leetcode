/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 545. Boundary of Binary Tree
 * 
 * https://leetcode.com/problems/boundary-of-binary-tree/description/
 * 
 * @author abel created on 2018/3/7 下午4:40
 * @version $Id$
 */
public class BoundaryOfBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        leftBoundary(root.left, res);
        leaves(root.left, res);
        leaves(root.right, res);
        rightBoundary(root.right, res);
        return res;
    }

    private void leftBoundary(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        res.add(root.val);
        if (root.left == null) {
            leftBoundary(root.right, res);
        } else {
            leftBoundary(root.left, res);
        }
    }

    private void rightBoundary(TreeNode root, List<Integer> res) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.right == null) {
            rightBoundary(root.left, res);
        } else {
            rightBoundary(root.right, res);
        }
        res.add(root.val);
    }

    private void leaves(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        leaves(root.left, res);
        leaves(root.right, res);

    }
}
