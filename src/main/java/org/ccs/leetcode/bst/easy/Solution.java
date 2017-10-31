/*
 * Copyright (c) 2016 Qunar.com. All Rights Reserved.
 */
package org.ccs.leetcode.bst.easy;

import org.ccs.leetcode.bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author abel created on 2017/10/25 下午5:19
 * @version $Id$
 */
public class Solution {

    /**
     * 530. Minimum Absolute Difference in BST
     * <p>
     * https://leetcode.com/problems/minimum-absolute-difference-in-bst
     * <p>
     * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any
     * two nodes.
     * </p>
     * 
     * @param root
     * @return
     */
    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }
        getMinimumDifference(root.left);
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;
        getMinimumDifference(root.right);
        return min;
    }
}
