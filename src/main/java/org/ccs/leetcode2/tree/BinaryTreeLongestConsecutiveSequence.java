/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * 
 * @author Abel created on 2018/1/31 14:59
 * @version $Id$
 */
public class BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getLongestConsecutive(root, 0, root.val - 1);
    }

    private int getLongestConsecutive(TreeNode root, int len, int val) {

        return 0;
    }
}
