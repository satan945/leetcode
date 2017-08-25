/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.algorithm.tree.travel;

import org.ccs.leetcode.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abel created on 2017/8/25 下午3:51
 * @version $Id$
 */
public class PostOrderTraversal {

    // Recursive
    public List<TreeNode> travelR(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        postOrderTravelRecursive(root, res);
        return res;
    }

    private void postOrderTravelRecursive(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        postOrderTravelRecursive(root.left, res);
        postOrderTravelRecursive(root.right, res);
        res.add(root);
    }

    // NonRecursive
    // using two stack

    // using linked list

    // using one stack and reverse

}
