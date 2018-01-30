/*
 * Copyright (c) 2017 Liqiang Fang. All Rights Reserved.
 */
package org.ccs.leetcode2.tree;

import org.ccs.leetcode.bean.TreeNode;

/**
 * 437. Path Sum III
 * <p>
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent
 * nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * </p>
 * 
 * @author Abel created on 2018/1/29 22:24
 * @version $Id$
 */
public class PathSumIII {
    private int count = 0;

    public int pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return count;
    }

    public void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        if (sum==0) {
            count++;
            return;
        }
        dfs(root.left, sum);
        dfs(root.left, sum - root.val);
        dfs(root.right, sum);
        dfs(root.right, sum - root.val);
    }

}
